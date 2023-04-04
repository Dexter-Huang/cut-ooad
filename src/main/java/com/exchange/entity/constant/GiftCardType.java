package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GiftCardType {
	THEME(0, "THEME"),
	CLASSIC(1, "CLASSIC"),
	BUSSINESS(2,"BUSSINESS"),
	FESTIVAL(3,"FESTIVAL");

	@EnumValue
	private Integer key;

	@JsonValue
	private String display;

	GiftCardType(Integer key, String display) {
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
