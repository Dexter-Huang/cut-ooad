package com.exchange.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Accounts;
import com.exchange.entity.Staff;
import com.exchange.dto.StaffAccountDto;
import com.exchange.dto.StaffQueryDto;
import com.exchange.service.AccountService;
import com.exchange.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	StaffService staffService;

	@Autowired
	AccountService accountService;

	@GetMapping("check")
	public ApiResponse isStaffExist(@RequestBody StaffAccountDto staffAccountDto){
		QueryWrapper<Accounts> queryWrapper=new QueryWrapper<Accounts>()
				.eq("accountType",1)
				.and(o->o.eq("name",staffAccountDto.getName())
					.or().eq("phone",staffAccountDto.getPhone()));
		Long siz=accountService.count(queryWrapper);
		if(siz==0L){
			return ApiResponse.success("无此用户",0);
		}else{
			return ApiResponse.fail("已存在该用户",1);
		}
	}

	public boolean isStaffListExist(@RequestBody List<StaffAccountDto> staffAccountDtos){
		for ( StaffAccountDto staffAccountDto: staffAccountDtos){
			if((int)isStaffExist(staffAccountDto).getData()==1){
				return true;
			}
		}
		return false;
	}

	@PostMapping("/add")
	public ApiResponse addStaff(@Validated @RequestBody StaffAccountDto staffAccountDto){
		if(isStaffExist(staffAccountDto).getCode()==200){
			String uuid= UUID.randomUUID().toString();
			staffAccountDto.setUuid(uuid);
			int ans=staffService.addOne(staffAccountDto);
			return ans>0? ApiResponse.success("注册成功",1): ApiResponse.fail("注册失败",0);
		}else {
			return ApiResponse.fail("已存在该用户",0);
		}
	}

	@PostMapping("addlist")
	public ApiResponse addStaffList(@Validated @RequestBody List<StaffAccountDto> staffAccountDtos){
		if(isStaffListExist(staffAccountDtos)){
			return ApiResponse.fail("已存在部分用户");
		}else {
			ArrayList<Accounts> accountsArray=new ArrayList<>();
			ArrayList<Staff> staffsArray=new ArrayList<>();
			for(StaffAccountDto staffAccountDto:staffAccountDtos){
				String uuid=UUID.randomUUID().toString();
				Accounts accounts=new Accounts();
				Staff staff =new Staff();
				accounts.setUuid(uuid);staff.setUuid(uuid);
				accounts.setAccountType(staffAccountDto.getAccountType());
				accounts.setName(staffAccountDto.getName());
				accounts.setAvatar(staffAccountDto.getAvatar());
				accounts.setEmail(staffAccountDto.getEmail());
				accounts.setPassword(SecureUtil.md5(staffAccountDto.getPassword()));
				accounts.setStatus(staffAccountDto.getStatus());
				accounts.setCreated(staffAccountDto.getCreated());
				accounts.setIdNum(staffAccountDto.getIdNum());
				accounts.setPhone(staffAccountDto.getPhone());
				accounts.setAge(staffAccountDto.getAge());
				accounts.setSex(staffAccountDto.getSex());
				accounts.setAccountType(staffAccountDto.getAccountType());

				staff.setStaffType(staffAccountDto.getStaffType());
				staff.setCredit(staff.getCredit());


				accounts.setAge(staffAccountDto.getAge());
				accountsArray.add(accounts);
				staffsArray.add(staff);
			}
			boolean ans=accountService.saveBatch(accountsArray);
			if(ans){
				ans=staffService.saveBatch(staffsArray);
			}

			return ans? ApiResponse.success("成功录入"): ApiResponse.fail("录入失败",0);
		}
	}

	@DeleteMapping("/del")
	public ApiResponse delStaff(@RequestBody StaffAccountDto staffAccountDto){
		return staffService.deleteOne(staffAccountDto)>0? ApiResponse.success("删除成功",1): ApiResponse.fail("删除失败");
	}

	@DeleteMapping("/dellist")
	public ApiResponse delStaffList(@RequestBody List<StaffAccountDto> staffAccountDtos){
		return staffService.deleteList(staffAccountDtos)>0? ApiResponse.success("批删除成功",1): ApiResponse.fail("批删除失败");
	}

	@PutMapping("/update")
	public ApiResponse updateUser(@RequestBody StaffAccountDto staffAccountDto){
		return staffService.updateOne(staffAccountDto)>0? ApiResponse.success("更新成功",1): ApiResponse.fail("更新失败");
	}

	@PutMapping("/updatelist")
	public ApiResponse updateUserList(@RequestBody List<StaffAccountDto> staffAccountDtos){
		ArrayList<Accounts> accountsArray=new ArrayList<>();
		ArrayList<Staff> staffsArray=new ArrayList<>();
		for(StaffAccountDto staffAccountDto:staffAccountDtos){
			String uuid=UUID.randomUUID().toString();
			Accounts accounts=new Accounts();
			Staff staff =new Staff();
			accounts.setUuid(uuid);staff.setUuid(uuid);
			accounts.setAccountType(staffAccountDto.getAccountType());
			accounts.setName(staffAccountDto.getName());
			accounts.setAvatar(staffAccountDto.getAvatar());
			accounts.setEmail(staffAccountDto.getEmail());
			accounts.setPassword(SecureUtil.md5(staffAccountDto.getPassword()));
			accounts.setStatus(staffAccountDto.getStatus());
			accounts.setCreated(staffAccountDto.getCreated());
			accounts.setIdNum(staffAccountDto.getIdNum());
			accounts.setPhone(staffAccountDto.getPhone());
			accounts.setAge(staffAccountDto.getAge());
			accounts.setSex(staffAccountDto.getSex());
			accounts.setAccountType(staffAccountDto.getAccountType());

			staff.setStaffType(staffAccountDto.getStaffType());
			staff.setCredit(staff.getCredit());
			accountsArray.add(accounts);
			staffsArray.add(staff);
		}

		boolean ans=accountService.updateBatchById(accountsArray);
		if(ans){
			ans=staffService.updateBatchById(staffsArray);
		}
		return ans? ApiResponse.success("批量更新成功",1): ApiResponse.fail("批量更新失败");
	}

	@GetMapping("/search")
	public ApiResponse searchStaff(@RequestBody StaffQueryDto staffQueryDto,
                                   @RequestParam(defaultValue = "1")Integer currentPage,
                                   @RequestParam(defaultValue = "20")Integer pageSize){
		return ApiResponse.success(staffService.selectList(staffQueryDto,currentPage,pageSize));
	}


}
