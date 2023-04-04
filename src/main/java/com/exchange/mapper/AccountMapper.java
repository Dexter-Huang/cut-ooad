package com.exchange.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.exchange.entity.Accounts;

import java.util.List;


public interface AccountMapper extends MPJBaseMapper<Accounts> {
	public int delAccountList(List<Integer> ids);
}
