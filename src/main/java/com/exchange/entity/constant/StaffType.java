package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StaffType {
    CLEANER(0, "CLEANER"),
    SERVER(1, "SERVER"),
    MANAGER(2, "MANAGER"),
    BOSS(3,"BOSS"),
    ADMIN(4,"ADMIN");

    @EnumValue
    private Integer key;

    @JsonValue
    private String display;

    StaffType(Integer key, String display) {
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
