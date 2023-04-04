package com.exchange.dto;

import com.exchange.entity.Accounts;
import com.exchange.entity.constant.StaffType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffAccountDto extends Accounts implements Serializable {

	private Integer hotelId;
	private StaffType staffType;
	private Integer credit;
	public StaffAccountDto(Accounts account,Integer hotelId, StaffType staffType, Integer credit) {
		this.setId(account.getId());
		this.setUuid(account.getUuid());
		this.setName(account.getName());
		this.setAvatar(account.getAvatar());
		this.setEmail(account.getEmail());
		this.setPassword(account.getPassword());
		this.setStatus(account.getStatus());
		this.setCreated(account.getCreated());
		this.setLastLogin(account.getLastLogin());
		this.setIdNum(account.getIdNum());
		this.setPhone(account.getPhone());
		this.setSex(account.getSex());
		this.setAge(account.getAge());
		this.setAccountType(account.getAccountType());

		this.setHotelId(hotelId);
		this.setStaffType(staffType);
		this.setCredit(credit);
	}
}
