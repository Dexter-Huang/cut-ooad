package com.exchange.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.exchange.entity.Accounts;
import com.exchange.entity.User;
import com.exchange.entity.constant.AccountStatus;
import com.exchange.entity.constant.Sex;
import com.exchange.entity.constant.UserType;
import com.exchange.dto.UserAccountDto;
import com.exchange.dto.UserQueryDto;
import com.exchange.mapper.*;
import com.exchange.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private static DateTimeFormatter defaultDateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UserMapper userMapper;




	private static int USERINFO_PAGESIZE=5;


	@Override
	public int updateCredit(Integer accountId) {
		return 0;
	}

	@Override
	public IPage<UserAccountDto> listUsers(Integer currentPage) {
		Page page =new Page(currentPage,USERINFO_PAGESIZE);
		IPage<UserAccountDto> list=accountMapper.selectJoinPage(page,UserAccountDto.class,
				new MPJLambdaWrapper<Accounts>()
						.selectAll(Accounts.class)
						.select(User::getCredit)
						.select(User::getUserType)
						.leftJoin(User.class,User::getUuid,Accounts::getUuid));

		return list;
	}

	@Override
	public int addOne(UserAccountDto userAccountDto) {
		User user=new User();
		user.setUuid(userAccountDto.getUuid());
		user.setCredit(userAccountDto.getCredit());
		user.setUserType(userAccountDto.getUserType());
		int ans=accountMapper.insert(userAccountDto);
		if(ans>0){
			ans=userMapper.insert(user);
		}
		return ans;
	}

	@Override
	public int addList(List<UserAccountDto> userAccountDtoList) {
		return 0;
	}

	@Override
	public int deleteOne(UserAccountDto userAccountDto) {
		int ans=accountMapper.delete(new QueryWrapper<Accounts>().eq("id",userAccountDto.getId()));
		if(ans==1){
			ans=userMapper.delete(new QueryWrapper<User>().eq("uuid",userAccountDto.getUuid()));
		}
		return ans;
	}

	@Override
	public int deleteList(List<UserAccountDto> userAccountDtoList) {
		List<Integer> ids=userAccountDtoList.stream().map(UserAccountDto::getId).collect(Collectors.toList());
		List<String> uuids=userAccountDtoList.stream().map(UserAccountDto::getUuid).collect(Collectors.toList());
		int ans=accountMapper.delAccountList(ids);
		if(ans>0){
			ans=userMapper.delUserList(uuids);
		}
		return ans;
	}

	@Override
	public int updateOne(UserAccountDto userAccountDto) {
		int ans=accountMapper.updateById(userAccountDto);
		if(ans>0){
			User user=new User();
			user.setUuid(userAccountDto.getUuid());
			user.setCredit(userAccountDto.getCredit());
			user.setUserType(userAccountDto.getUserType());
			ans=userMapper.update(user,new QueryWrapper<User>().eq("uuid",userAccountDto.getUuid()));
		}
		return ans;
	}

	@Override
	public int updateList(Map<String,Object> data) {
		List<UserAccountDto> list=(List<UserAccountDto>)data.get("list");
		for(int i=0;i<list.size();i++){
			list.set(i, JSON.parseObject(JSON.toJSONString(list.get(i)),  UserAccountDto.class));
		}
		UserAccountDto form= JSON.parseObject(JSON.toJSONString(data.get("form")),  UserAccountDto.class);
		System.out.println(form);
		System.out.println(list);
		boolean isSetName=!form.getName().equals("");
		boolean isSetAvatar=!(form.getAvatar().equals(""));
		boolean isSetSex=!(form.getSex()==null);
		boolean isSetStatus=!(form.getStatus()==null);
		boolean isSetUserType=!(form.getUserType()==null);
		boolean isSetCredit=!(form.getCredit()==null);
		boolean isSetEmail=!form.getEmail().equals("");
		boolean isSetPassword=!form.getPassword().equals("");
		boolean isSetPhone=!form.getPhone().equals("");
		boolean isSetAge=!(form.getAge()==null);

		List<Integer> accountId=list.stream().map(UserAccountDto::getId).collect(Collectors.toList());
		List<String> useruuId=list.stream().map(UserAccountDto::getUuid).collect(Collectors.toList());

		LambdaUpdateWrapper<Accounts> accountsWrapper=new LambdaUpdateWrapper<>();
		accountsWrapper.in(Accounts::getId,accountId);
		LambdaUpdateWrapper<User> userWrapper=new LambdaUpdateWrapper<>();
		userWrapper.in(User::getUuid,useruuId);
		if(isSetName)
			accountsWrapper.set(Accounts::getName,form.getName());
		if(isSetSex)
			accountsWrapper.set(Accounts::getSex,form.getSex());
		if(isSetAvatar)
			accountsWrapper.set(Accounts::getAvatar,form.getAvatar());
		if(isSetStatus)
			accountsWrapper.set(Accounts::getStatus,form.getStatus());
		if(isSetUserType)
			userWrapper.set(User::getUserType,form.getStatus());
		if(isSetCredit)
			userWrapper.set(User::getCredit,form.getCredit());
		if(isSetEmail)
			accountsWrapper.set(Accounts::getEmail,form.getEmail());
		if(isSetPassword)
			accountsWrapper.set(Accounts::getPassword,form.getPassword());
		if(isSetPhone)
			accountsWrapper.set(Accounts::getPhone,form.getPhone());
		if(isSetAge)
			accountsWrapper.set(Accounts::getAge,form.getAge());

		int rows=accountMapper.update(null,accountsWrapper);
		if(rows==1){
			rows=userMapper.update(null,userWrapper);
		}
		return rows;
	}

	@Override
	public IPage<UserAccountDto> selectBySingleCondition(Map<String,Object> data) {
		MPJLambdaWrapper<Accounts> wrapper=new MPJLambdaWrapper<Accounts>()
				.selectAll(Accounts.class)
				.select(User::getCredit)
				.select(User::getUserType)
				.leftJoin(User.class,User::getUuid,Accounts::getUuid);
		String searchKey=(String)data.get("searchKey");
		switch (searchKey){
			case"name":
				String name=(String)data.get("name");
				wrapper.like(Accounts::getName,name);
				break;
			case"sex":
				String sex=(String)data.get("sex");
				wrapper.eq(Accounts::getSex,sex.equals("MAN")?Sex.MAN:Sex.WOMAN);
				break;
			case"userType":
				String userType=(String)data.get("userType");
				System.out.println("userType");
				System.out.println(userType);
				wrapper.eq(User::getUserType,userType.equals("USER")?UserType.USER:UserType.VIP_USER);
				break;
			case"age":
				String str_age=(String)data.get("age");
				String str_age2=(String)data.get("age2");
				System.out.println(str_age);
				System.out.println(str_age2);
				Integer age=str_age==null||str_age.equals("")?-1:Integer.parseInt(str_age);
				Integer age2=str_age2==null||str_age2.equals("")?200:Integer.parseInt(str_age2);
				wrapper.ge(Accounts::getAge,age)
						.le(Accounts::getAge,age2);
				break;
			case"created":
				LocalDateTime created=LocalDateTime.parse((String)data.get("created"),defaultDateTimeFormatter);
				LocalDateTime created2=LocalDateTime.parse((String)data.get("created2"),defaultDateTimeFormatter);
				System.out.println(created);
				System.out.println(created2);
				wrapper.ge(Accounts::getCreated,created)
					.le(Accounts::getCreated,created2);
				break;
			case"email":
				String email=(String)data.get("email");
				wrapper.like(Accounts::getEmail,email);
				break;
			case "phone":
				String phone=(String)data.get("phone");
				System.out.println(phone);
				wrapper.like(Accounts::getPhone,phone);
				break;
			case"status":
				String accountStatus=(String)data.get("status");
				if(accountStatus.equals("OK"))
					wrapper.eq(Accounts::getStatus,AccountStatus.OK);
				if(accountStatus.equals("LOCKED"))
					wrapper.eq(Accounts::getStatus,AccountStatus.LOCKED);
				if(accountStatus.equals("DELETED"))
					wrapper.eq(Accounts::getStatus,AccountStatus.DELETED);
				break;
			case"credit":
				String str_credit=(String)data.get("credit");
				String str_credit2=(String)data.get("credit2");
				System.out.println(str_credit);
				System.out.println(str_credit2);
				Integer credit=str_credit==null||str_credit.equals("")?-1:Integer.parseInt(str_credit);
				Integer credit2=str_credit2==null||str_credit2.equals("")?Integer.MAX_VALUE:Integer.parseInt(str_credit2);
				wrapper.ge(User::getCredit,credit)
						.le(User::getCredit,credit2);
				break;
			default:
				break;
		}
		System.out.println((Integer)data.get("currentPage"));
		IPage<UserAccountDto> list=accountMapper.selectJoinPage(new Page<>((Integer)data.get("currentPage"),USERINFO_PAGESIZE),UserAccountDto.class, wrapper);

		return list;
	}

	@Override
	public IPage<UserAccountDto> selectList(Map<String,Object> data) {
		UserQueryDto userQueryDto=JSON.parseObject(JSON.toJSONString(data.get("searchlist")),UserQueryDto.class);
		Integer currentPage=(Integer)data.get("currentPage");
		MPJLambdaWrapper<Accounts> wrapper=	new MPJLambdaWrapper<Accounts>()
				.selectAll(Accounts.class)
				.select(User::getCredit)
				.select(User::getUserType)
				.leftJoin(User.class,User::getUuid,Accounts::getUuid);
		System.out.println(userQueryDto.getCreated());

		wrapper.like(Accounts::getName,userQueryDto.getName());
		wrapper.like(Accounts::getEmail,userQueryDto.getEmail());


		List<Integer> statusArray=new ArrayList<>();
		if(userQueryDto.getIsOK()!=null&&userQueryDto.getIsOK()==true)
			statusArray.add(0);
		if(userQueryDto.getIsDeleted()!=null&&userQueryDto.getIsDeleted()==true)
			statusArray.add(1);
		if(userQueryDto.getIsLocked()!=null&&userQueryDto.getIsLocked()==true)
			statusArray.add(2);
		if(statusArray.size()==0){
			statusArray.add(0);
			statusArray.add(1);
			statusArray.add(2);
		}
		wrapper.in(Accounts::getStatus,statusArray);


		if(!userQueryDto.getCreated().equals("")){
			LocalDateTime created=LocalDateTime.parse(userQueryDto.getCreated(),defaultDateTimeFormatter);
			wrapper.ge(Accounts::getCreated,created);
		}
		if(!userQueryDto.getCreated2().equals("")){
			LocalDateTime created2=LocalDateTime.parse(userQueryDto.getCreated2(),defaultDateTimeFormatter);
			wrapper.le(Accounts::getCreated,created2);
		}

		wrapper.like(Accounts::getPhone,userQueryDto.getPhone());

		List<Integer> sexArray=new ArrayList<>();
		if(userQueryDto.getIsMan()!=null&&userQueryDto.getIsMan()==true)
			sexArray.add(1);
		if(userQueryDto.getIsWoman()!=null&&userQueryDto.getIsWoman()==true)
			sexArray.add(0);
		if(sexArray.size()==0){
			sexArray.add(0);
			sexArray.add(1);
		}

		wrapper.in(Accounts::getSex,sexArray);

		if(userQueryDto.getAge()!=null)
			wrapper.ge(Accounts::getAge,userQueryDto.getAge());
		if(userQueryDto.getAge2()!=null)
			wrapper.le(Accounts::getAge,userQueryDto.getAge2());

		if(userQueryDto.getCredit()!=null)
			wrapper.ge(User::getCredit,userQueryDto.getCredit());
		if(userQueryDto.getCredit2()!=null)
			wrapper.le(User::getCredit,userQueryDto.getCredit2());

		List<Integer> vipArray=new ArrayList<>();
		if(userQueryDto.getIsVIP()!=null&&userQueryDto.getIsVIP()==true)
			vipArray.add(1);
		if(userQueryDto.getIsNotVIP()!=null&&userQueryDto.getIsNotVIP()==true)
			vipArray.add(0);
		if(vipArray.size()==0){
			vipArray.add(0);
			vipArray.add(1);
		}
		wrapper.in(User::getUserType,vipArray);
		IPage<UserAccountDto> list=accountMapper.selectJoinPage(new Page<>(currentPage,USERINFO_PAGESIZE),UserAccountDto.class, wrapper);
		return list;
	}
}
