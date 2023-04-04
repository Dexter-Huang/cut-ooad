package com.exchange.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exchange.entity.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exchange.dto.UserAccountDto;

import java.util.List;
import java.util.Map;


public interface UserService extends IService<User> {

	public int updateCredit(Integer accountId);

	public IPage<UserAccountDto> listUsers(Integer currentPage);

	public int addOne (UserAccountDto userAccountDto);
	public int addList(List<UserAccountDto> userAccountDtoList);

	public int deleteOne(UserAccountDto userAccountDto);
	public int deleteList(List<UserAccountDto> userAccountDtoList);

	public int updateOne(UserAccountDto userAccountDto);
	public int updateList(Map<String,Object> data);


	public IPage<UserAccountDto> selectBySingleCondition(Map<String,Object> data);
	public IPage<UserAccountDto> selectList(Map<String,Object> data);
}
