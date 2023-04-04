package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {
	USER(0, "USER"),
	STAFF(1, "STAFF");

	@EnumValue
	private Integer key;

	@JsonValue
	private String display;

	AccountType(Integer key, String display) {
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
