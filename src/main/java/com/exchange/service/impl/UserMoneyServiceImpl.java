package com.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exchange.entity.UserMoney;
import com.exchange.mapper.UserMoneyMapper;
import com.exchange.service.UserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMoneyServiceImpl extends ServiceImpl<UserMoneyMapper, UserMoney> implements UserMoneyService {
	@Autowired
	private UserMoneyMapper userMoneyMapper;
	
}
