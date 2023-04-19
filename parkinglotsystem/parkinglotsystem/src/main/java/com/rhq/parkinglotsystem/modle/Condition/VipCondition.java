package com.rhq.parkinglotsystem.modle.Condition;

import lombok.Data;

import java.util.Date;

@Data
public class VipCondition {

    /**
     * 月卡卡号
     */
    private String cardId;

    /**
     * 开卡日期
     */
    private Date startTime;
}
