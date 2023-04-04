package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FileType {
	IMG(0, "IMG"),
	VIDEO(1, "VIDEO");

	@EnumValue
	private Integer key;

	@JsonValue
	private String display;

	FileType(Integer key, String display) {
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
