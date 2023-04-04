package com.exchange.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exchange.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exchange.dto.StaffAccountDto;
import com.exchange.dto.StaffQueryDto;

import java.util.List;


public interface StaffService extends IService<Staff> {
	public int addOne(StaffAccountDto StaffAccountDto);

	public int deleteOne(StaffAccountDto StaffAccountDto);
	public int deleteList(List<StaffAccountDto> StaffAccountDtoList);

	public int updateOne(StaffAccountDto StaffAccountDto);
	public int updateList(List<StaffAccountDto> StaffAccountDtoList);

	public IPage<StaffAccountDto> selectList(StaffQueryDto staffQueryDto, Integer currentPage, Integer pageSize);
}
