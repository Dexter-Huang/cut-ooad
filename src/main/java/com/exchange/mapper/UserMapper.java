package com.exchange.mapper;

//import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.exchange.entity.User;

import java.util.List;


public interface UserMapper extends MPJBaseMapper<User> {
	public int delUserList(List<String> uuids);
}
