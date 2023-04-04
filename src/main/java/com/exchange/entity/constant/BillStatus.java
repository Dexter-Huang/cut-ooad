package com.exchange.entity.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BillStatus {
    CREATED_BUT_UNPAID (0,"CREATED_BUT_UNPAID"),
    BOOKED_BUT_UNLIVED (1,"BOOKED_BUT_UNLIVED"),
    DEPOSIT_AND_LIVED (2,"DEPOSIT_AND_LIVED"),
    PAID_BUT_UNREVIEWED(3,"PAID_BUT_UNREVIEWED"),
    PAID_AND_REVIEWED(4,"PAID_AND_REVIEWED"),
    BOOKED_BUT_REFUND (5,"BOOKED_BUT_REFUND"),
    LIVED_AND_REFUND (6,"LIVED_AND_REFUND");

    @EnumValue
    private Integer key;

    @JsonValue
    private String display;

    BillStatus(Integer key, String display) {
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
