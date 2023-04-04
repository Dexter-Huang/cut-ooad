package com.exchange.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.exchange.entity.Staff;

import java.util.List;


public interface StaffMapper extends MPJBaseMapper<Staff> {
	public int delStaffList(List<String> uuids);
}
