package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReviewType {
	LIVED(0, "LIVED"),
	GOOD(1, "GOOD"),
	BAD(2,"BAD");

	@EnumValue
	private Integer key;

	@JsonValue
	private String display;

	ReviewType(Integer key, String display) {
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
