package com.exchange.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Accounts;
import com.exchange.entity.User;
import com.exchange.dto.UserAccountDto;
import com.exchange.service.AccountService;
import com.exchange.service.UserService;
import com.exchange.util.mq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Resource
    private MessageSender messageSender;


    @PutMapping("/sendMsgHello")
    public ApiResponse sendMsg(String msg){
        messageSender.sendMessage("boot_queue",msg);
        return ApiResponse.success();
    }

    @GetMapping("/users")
    public ApiResponse list(@RequestParam(defaultValue = "1")Integer currentPage){
        System.out.println("111");
        return ApiResponse.success(userService.listUsers(currentPage));
    }
    @GetMapping("/checkname")
    public ApiResponse checkName(@RequestParam("name") String name){
        QueryWrapper<Accounts> queryWrapper=new QueryWrapper<Accounts>()
                .eq("accountType",0)
                .and(o->o.eq("name",name));
        Long siz=accountService.count(queryWrapper);
        if(siz==0L){
            return ApiResponse.success("无此用户",0);
        }else{
            return ApiResponse.success("已存在该用户",1);
        }
    }
    @GetMapping("/checkphone")
    public ApiResponse checkPhone(@RequestParam("phone") String phone){
        QueryWrapper<Accounts> queryWrapper=new QueryWrapper<Accounts>()
                .eq("accountType",0)
                .and(o->o.eq("phone",phone));
        Long siz=accountService.count(queryWrapper);
        if(siz==0L){
            return ApiResponse.success("无此用户",0);
        }else{
            return ApiResponse.success("已存在该用户",1);
        }
    }


    @PostMapping("/check")
    public ApiResponse isUserExist(@RequestBody UserAccountDto userAccountDto){
        QueryWrapper<Accounts> queryWrapper=new QueryWrapper<Accounts>()
                .eq("accountType",0)
                .and(o->o.eq("name",userAccountDto.getName())
                    .or().eq("phone",userAccountDto.getPhone()));
        Long siz=accountService.count(queryWrapper);
        if(siz==0L){
            return ApiResponse.success("无此用户",0);
        }else{
            return ApiResponse.fail("已存在该用户",1);
        }
    }

    public boolean isUserListExist(@RequestBody List<UserAccountDto> userAccountDtos){
        for ( UserAccountDto userAccountDto: userAccountDtos){
            if((int)isUserExist(userAccountDto).getData()==1){
                return true;
            }
        }
        return false;
    }

    /**
     * 注册账户
     */
    @PostMapping("/add")
    public ApiResponse addUser(@Validated @RequestBody UserAccountDto userAccountDto){
        if(isUserExist(userAccountDto).getCode()==200){
            String uuid= UUID.randomUUID().toString();
            userAccountDto.setUuid(uuid);
            int ans=userService.addOne(userAccountDto);
            return ans>0? ApiResponse.success("注册成功",1): ApiResponse.fail("注册失败",0);
        }else {
            return ApiResponse.fail("已存在该用户",0);
        }
    }

    @PostMapping("/addlist")

    public ApiResponse addUserList(@Validated @RequestBody List<UserAccountDto> userAccountDtos){
        if(isUserListExist(userAccountDtos)){
            return ApiResponse.fail("已存在部分用户");
        }else {
            ArrayList<Accounts> accountsArray=new ArrayList<>();
            ArrayList<User> usersArray=new ArrayList<>();
            for(UserAccountDto userAccountDto:userAccountDtos){
                String uuid=UUID.randomUUID().toString();
                Accounts accounts=new Accounts();
                User user =new User();
                accounts.setUuid(uuid);user.setUuid(uuid);
                accounts.setAccountType(userAccountDto.getAccountType());
                accounts.setName(userAccountDto.getName());
                accounts.setAvatar(userAccountDto.getAvatar());
                accounts.setEmail(userAccountDto.getEmail());
                accounts.setPassword(SecureUtil.md5(userAccountDto.getPassword()));
                accounts.setStatus(userAccountDto.getStatus());
                accounts.setCreated(userAccountDto.getCreated());
                accounts.setIdNum(userAccountDto.getIdNum());
                accounts.setPhone(userAccountDto.getPhone());
                accounts.setAge(userAccountDto.getAge());
                accounts.setSex(userAccountDto.getSex());
                accounts.setAccountType(userAccountDto.getAccountType());

                user.setUserType(userAccountDto.getUserType());
                user.setCredit(user.getCredit());


                accounts.setAge(userAccountDto.getAge());
                accountsArray.add(accounts);
                usersArray.add(user);
            }
            boolean ans=accountService.saveOrUpdateBatch(accountsArray);
            if(ans){
                ans=userService.saveOrUpdateBatch(usersArray);
            }
            return ans? ApiResponse.success("成功录入"): ApiResponse.fail("录入失败",0);
        }
    }

    @PostMapping("/del")
    public ApiResponse delUser(@RequestBody UserAccountDto userAccountDto){
        System.out.println("---->"+userAccountDto.getId());
        return userService.deleteOne(userAccountDto)>0? ApiResponse.success("删除成功",1): ApiResponse.fail("删除失败");
    }

    @PostMapping("/dellist")
    public ApiResponse delUserList(@RequestBody List<UserAccountDto> userAccountDtos){
        return userService.deleteList(userAccountDtos)>0? ApiResponse.success("批删除成功",1): ApiResponse.fail("批删除失败");
    }

    @PostMapping("/update")
    public ApiResponse updateUser(@RequestBody UserAccountDto userAccountDto){
        System.out.println(userAccountDto.toString());
        return userService.updateOne(userAccountDto)>0? ApiResponse.success("更新成功",1): ApiResponse.fail("更新失败");
    }

    @PostMapping("/updatelist")
    public ApiResponse updateUserList(@RequestBody Map<String,Object> data){
        int ans=userService.updateList(data);
        return ans>0? ApiResponse.success("批量更新成功",1): ApiResponse.fail("批量更新失败");
    }

    @PostMapping("/searchlist")
    public ApiResponse searchUser(@RequestBody Map<String,Object> data){
        System.out.println(data);
        return ApiResponse.success(userService.selectList(data));
    }

    @PostMapping("searchbycondition")
    public ApiResponse searchByCondition(@RequestBody Map<String,Object> data){

        System.out.println(data);
        return ApiResponse.success(userService.selectBySingleCondition(data));
    }

    @PostMapping("/searchcredit")
    public ApiResponse searchCredit(@RequestBody Map<String,Object> data){
        String curName = (String) data.get("name");
        List<Accounts> list1 = accountService.list(new QueryWrapper<Accounts>().eq("name", curName));
        String uuid = list1.get(0).getUuid();
        List<User> list2 = userService.list(new QueryWrapper<User>().eq("uuid", uuid));
        long credit = list2.get(0).getCredit();
        return ApiResponse.success(credit);
    }

}
