package com.exchange.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.exchange.entity.Accounts;
import com.exchange.dto.LoginDto;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Staff;
import com.exchange.entity.User;
import com.exchange.dto.StaffAccountDto;
import com.exchange.dto.UserAccountDto;
import com.exchange.service.AccountService;
import com.exchange.service.StaffService;
import com.exchange.service.UserService;
import com.exchange.util.CaptchaUtils;
import com.exchange.util.shiro.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("userregister")
    public ApiResponse userRegister(@Validated @RequestBody UserAccountDto userAccountDto){
//        System.out.println(userAccountDto);
//        System.out.println("!!!");
        String uuid= UUID.randomUUID().toString();
        userAccountDto.setPassword(SecureUtil.md5(userAccountDto.getPassword()));
        userAccountDto.setUuid(uuid);

        int isadd=userService.addOne(userAccountDto);

        return isadd>0? ApiResponse.success("注册成功",1): ApiResponse.fail("注册成功");
    }

    @PostMapping("staffregister")
    public ApiResponse staffRegister(@Validated @RequestBody StaffAccountDto staffAccountDto){
        String uuid= UUID.randomUUID().toString();
        staffAccountDto.setPassword(SecureUtil.md5(staffAccountDto.getPassword()));
        staffAccountDto.setUuid(uuid);

        int isadd=staffService.addOne(staffAccountDto);

        return isadd>0? ApiResponse.success("注册成功",1): ApiResponse.fail("注册成功");
    }




    @PostMapping("/userlogin")
    public ApiResponse userLogin(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        Accounts account=null;
        switch (loginDto.getLoginType()){
            case "name"://用户名方式登录
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("name", loginDto.getName()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                Assert.isTrue(account.getPassword().equals(SecureUtil.md5(loginDto.getPassword())),"密码不正确");
                break;
            case "phone"://用户手机号登录
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("phone", loginDto.getPhone()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                Assert.isTrue(account.getPassword().equals(SecureUtil.md5(loginDto.getPassword())),"密码不正确");
                break;
            case "email"://用户邮箱登录
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("name",loginDto.getName()).eq("email", loginDto.getEmail()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                break;
            default:
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("name", loginDto.getName()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                Assert.isTrue(account.getPassword().equals(SecureUtil.md5(loginDto.getPassword())),"密码不正确");
                break;
        }

        String captchaKey = CaptchaUtils.getCaptchaKey(request);
        String cacheCaptcha = stringRedisTemplate.opsForValue().get(captchaKey);
        String captcha=loginDto.getCaptchaCode();
        System.out.println("hhhh "+captcha);
        Assert.notNull(cacheCaptcha,"服务器异常");
        if(cacheCaptcha.equalsIgnoreCase(captcha)) {
            stringRedisTemplate.delete(captchaKey);
            //TODO 发送验证码
            String jwt = jwtUtils.generateToken(account.getId());
            stringRedisTemplate.opsForValue().set("user-service:token:accountId:"+account.getId(),jwt,180, TimeUnit.MINUTES);


            response.setHeader("Authorization", jwt);
            response.setHeader("Access-control-Expose-Headers", "Authorization");

            User user = userService.getOne(new QueryWrapper<User>().eq("uuid", account.getUuid()).last("limit 1"));

            return ApiResponse.success(MapUtil.builder()
                    .put("id", account.getId())
                    .put("name", account.getName())
                    .put("avatar", account.getAvatar())
                    .put("userType",user.getUserType())
                    .map()
            );
        }else {
            return ApiResponse.fail("图形验证码错误");
        }
    }

    @PostMapping("/stafflogin")
    public ApiResponse staffLogin(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        Accounts account=null;
        switch (loginDto.getLoginType()){
            case "name"://用户名方式登录
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("name", loginDto.getName()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                String code = SecureUtil.md5(loginDto.getPassword());
                Assert.isTrue(account.getPassword().equals(code),"密码不正确");
                break;
            case "phone"://用户手机号登录
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("phone", loginDto.getPhone()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                Assert.isTrue(account.getPassword().equals(SecureUtil.md5(loginDto.getPassword())),"密码不正确");
                break;
            case "email"://用户邮箱登录
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("name",loginDto.getName()).eq("email", loginDto.getEmail()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                break;
            default:
                account = accountService.getOne(new QueryWrapper<Accounts>().eq("name", loginDto.getName()).le("accountType",1).last("limit 1"));
                Assert.notNull(account, "用户不存在");
                Assert.isTrue(account.getPassword().equals(SecureUtil.md5(loginDto.getPassword())),"密码不正确");
                break;
        }

        Assert.notNull(account, "用户不存在");
        String key = CaptchaUtils.getCaptchaKey(request);
        String cacheCaptcha = stringRedisTemplate.opsForValue().get(key);
        String captcha=loginDto.getCaptchaCode();
        Assert.notNull(cacheCaptcha,"服务器异常");
        if(cacheCaptcha.equalsIgnoreCase(captcha)) {
            stringRedisTemplate.delete(key);
            //TODO 发送验证码
            String jwt = jwtUtils.generateToken(account.getId());
            stringRedisTemplate.opsForValue().set("staff-service:token:accountId:"+account.getId(),jwt,180, TimeUnit.MINUTES);

            response.setHeader("Authorization", jwt);
            response.setHeader("Access-control-Expose-Headers", "Authorization");

            Staff staff = staffService.getOne(new QueryWrapper<Staff>().eq("uuid", account.getUuid()).last("limit 1"));

            return ApiResponse.success(MapUtil.builder()
                    .put("id", account.getId())
                    .put("name", account.getName())
                    .put("avatar", account.getAvatar())
                    .put("email", account.getEmail())
                    .map()
            );
        }else {
            return ApiResponse.fail("图形验证码错误");
        }
    }



    @RequiresAuthentication
    @PostMapping("/resetpassword")
    public ApiResponse resetPassword(@Validated @RequestBody LoginDto loginDto){
//        System.out.println(loginDto.toString());
        boolean isupdate= accountService.update(new UpdateWrapper<Accounts>().eq("name",loginDto.getName())
                                .set("password",SecureUtil.md5(loginDto.getPassword())));
        return isupdate? ApiResponse.success("更新密码成功",1): ApiResponse.fail("更新密码失败");
    }

    //获取敏感信息
    @RequiresAuthentication
    @GetMapping("/getuserinfo/{id}")
    public ApiResponse getUserInfo(@PathVariable(name = "id") Integer id, HttpServletRequest httpServletRequest){
        String client_jwt=httpServletRequest.getHeader("Authorization");
//        System.out.println(client_jwt);
        String server_jwt=stringRedisTemplate.opsForValue().get("user-service:token:accountId:"+id);
//        System.out.println(server_jwt);
        if(server_jwt==null||!server_jwt.equals(client_jwt)){
            return ApiResponse.success("用户凭证错误");
        }else{


            return ApiResponse.success("HHH@");
        }
    }


    @RequiresAuthentication
    @GetMapping("/logout")
    public ApiResponse logout() {
        SecurityUtils.getSubject().logout();
        return ApiResponse.success(null);
    }

    @GetMapping("/getchatavatar/{chatname}")
    public String getChatAvatar(@PathVariable(name="chatname")String chatName){
        System.out.println("chatName "+chatName);
        String avatar=accountService.getOne(new QueryWrapper<Accounts>().eq("name",chatName).last("limit 1")).getAvatar();
        System.out.println(avatar);
        return avatar;
    }

}
