package com.rhq.parkinglotsystem.modle.Condition;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerCondition {

    /**
     * 顾客openid
     */
    private String openId;
    /**
     * 创建时间
     */
    private String createTime;
}
