package com.exchange.service;

import com.exchange.vo.ApiResponse;
import com.exchange.entity.Accounts;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exchange.dto.LoginDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface AccountService extends IService<Accounts> {
	int sendEmailCode(String email);
	ApiResponse cookieLogin(HttpServletRequest request, HttpServletResponse response, LoginDto loginDto);
	String getAvatar(String username);
	List<String> getAllUserNames();

}
