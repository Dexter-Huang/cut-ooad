package com.exchange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Accounts;
import com.exchange.dto.LoginDto;
import com.exchange.mapper.AccountMapper;
import com.exchange.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Accounts> implements AccountService {
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public int sendEmailCode(String email){
		return 0;
	}

	@Override
	public ApiResponse cookieLogin(HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) {
		return null;
	}

	@Override
	public String getAvatar(String username) {
//		return accountMapper(new QueryWrapper<Accounts>().eq("name",username));
		return "";
	}

	@Override
	public List<String> getAllUserNames() {
		List<String> names=accountMapper.selectList(new QueryWrapper<Accounts>().eq("accountType",0))
				.stream().map(Accounts::getName).collect(Collectors.toList());
		return names;
	}


}
