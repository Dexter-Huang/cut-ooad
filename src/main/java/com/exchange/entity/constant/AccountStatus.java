package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatus {
	OK(0, "OK"),
	LOCKED(1, "LOCKED"),
	DELETED(2,"DELETED");

	@EnumValue
	private Integer key;

	@JsonValue
	private String display;

	AccountStatus(Integer key, String display) {
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
