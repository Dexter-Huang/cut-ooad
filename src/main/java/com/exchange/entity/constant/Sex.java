package com.exchange.entity.constant;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex {
    WOMAN(0, "WOMAN"),
    MAN(1, "MAN");

    @EnumValue
    private Integer key;

    @JsonValue
    private String display;

    Sex(Integer key, String display) {
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