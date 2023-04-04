package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
	USER(0, "USER"),
	VIP_USER(1, "VIPUSER");

	@EnumValue
	private Integer key;

	@JsonValue
	private String display;

	UserType(Integer key, String display) {
		this.key = key;
		this.display = display;
	}

	public Integer getKey() {
		return key;
	}

	public String getDisplay() {
		return display;
	}
}
