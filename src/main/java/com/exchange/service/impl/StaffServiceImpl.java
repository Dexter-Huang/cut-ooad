package com.exchange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.exchange.entity.Accounts;
import com.exchange.entity.Staff;
import com.exchange.entity.constant.AccountStatus;
import com.exchange.entity.constant.Sex;
import com.exchange.entity.constant.StaffType;
import com.exchange.dto.StaffAccountDto;
import com.exchange.dto.StaffQueryDto;
import com.exchange.mapper.AccountMapper;
import com.exchange.mapper.StaffMapper;
import com.exchange.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private StaffMapper staffMapper;

	@Override
	public int addOne(StaffAccountDto staffAccountDto) {
		Staff staff =new Staff();
		staff.setId(staffAccountDto.getId());
		staff.setHotelId(staffAccountDto.getHotelId());
		staff.setStaffType(staffAccountDto.getStaffType());
		staff.setUuid(staffAccountDto.getUuid());

		int ans=accountMapper.insert(staffAccountDto);
		if(ans>0){
			ans=staffMapper.insert(staff);
		}
		return ans;
	}

//	@Override
//	public int addList(List<StaffAccountDto> StaffAccountDtoList) {
//		return 0;
//	}

	@Override
	public int deleteOne(StaffAccountDto staffAccountDto) {

		int ans=accountMapper.delete(new QueryWrapper<Accounts>().eq("id",staffAccountDto.getId()));
		if(ans==1){
			ans=staffMapper.delete(new QueryWrapper<Staff>().eq("uuid",staffAccountDto.getUuid()));
		}
		return ans;
	}

	@Override
	public int deleteList(List<StaffAccountDto> staffAccountDtoList) {
		List<Integer> ids=staffAccountDtoList.stream().map(StaffAccountDto::getId).collect(Collectors.toList());
		List<String> uuids=staffAccountDtoList.stream().map(StaffAccountDto::getUuid).collect(Collectors.toList());
		int ans=accountMapper.delAccountList(ids);
		if(ans>0){
			ans=staffMapper.delStaffList(uuids);
		}
		return ans;
	}


	@Override
	public int updateOne(StaffAccountDto staffAccountDto) {
		int ans=accountMapper.updateById(staffAccountDto);
		if(ans>0){
			Staff staff=new Staff();
			staff.setUuid(staffAccountDto.getUuid());
			staff.setCredit(staffAccountDto.getCredit());
			staff.setStaffType(staffAccountDto.getStaffType());
			ans=staffMapper.update(staff,new QueryWrapper<Staff>().eq("uuid",staffAccountDto.getUuid()));
		}
		return ans;
	}

	@Override
	public int updateList(List<StaffAccountDto> StaffAccountDtoList) {
		return 0;
	}

	@Override
	public IPage<StaffAccountDto> selectList(StaffQueryDto staffQueryDto, Integer currentPage, Integer pageSize) {
		IPage<StaffAccountDto> list=accountMapper.selectJoinPage(new Page<>(currentPage,pageSize),StaffAccountDto.class,
				new MPJLambdaWrapper<Accounts>()
						.selectAll(Accounts.class)
						.select(Staff::getCredit)
						.select(Staff::getStaffType)
						.leftJoin(Staff.class,Staff::getUuid,Accounts::getUuid)

						.like(Accounts::getName,!staffQueryDto.getName().equals("")?staffQueryDto.getName():null)
						.like(Accounts::getEmail,!staffQueryDto.getEmail().equals("")?staffQueryDto.getEmail():null)
						.and(o->o.eq(Accounts::getStatus,staffQueryDto.getIsOK()==1?AccountStatus.OK:null)
								.or().eq(Accounts::getStatus,staffQueryDto.getIsLocked()==1?AccountStatus.LOCKED:null)
								.or().eq(Accounts::getStatus,staffQueryDto.getIsDeleted()==1?AccountStatus.DELETED:null))

						.ge(Accounts::getCreated,staffQueryDto.getCreated())
						.le(Accounts::getCreated,staffQueryDto.getCreated2())
						.ge(Accounts::getLastLogin,staffQueryDto.getLastLogin())
						.le(Accounts::getLastLogin,staffQueryDto.getLastLogin2())

						.like(Accounts::getPhone,!staffQueryDto.getPhone().equals("")?staffQueryDto.getPhone():null)
						.and(o->o.eq(Accounts::getSex, staffQueryDto.getIsMan()==1?Sex.MAN:null)
								.or().eq(Accounts::getSex,staffQueryDto.getIsWoman()==1?Sex.WOMAN:null))
						.ge(Accounts::getAge,staffQueryDto.getAge()!=-1?staffQueryDto.getAge():null)
						.le(Accounts::getAge,staffQueryDto.getAge2()!=-1?staffQueryDto.getAge2():null)

						.eq(staffQueryDto.getHotelId()!=0,Staff::getHotelId,staffQueryDto.getHotelId())
						.and(o->o.eq(Staff::getStaffType,staffQueryDto.getIsAdmin()==1? StaffType.ADMIN:null)
							.or().eq(Staff::getStaffType,staffQueryDto.getIsBoss()==1? StaffType.BOSS:null)
							.or().eq(Staff::getStaffType,staffQueryDto.getIsManager()==1? StaffType.MANAGER:null)
							.or().eq(Staff::getStaffType,staffQueryDto.getIsServer()==1? StaffType.SERVER:null)
							.or().eq(Staff::getStaffType,staffQueryDto.getIsCleaner()==1? StaffType.CLEANER:null)

						)
						.ge(Staff::getCredit,staffQueryDto.getCredit()!=-1?staffQueryDto.getCredit():null)
								.le(Staff::getCredit,staffQueryDto.getCredit2()!=-1?staffQueryDto.getCredit2():null)


		);
		return list;
	}

}
