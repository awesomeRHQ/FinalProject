package com.rhq.parkinglotsystem.modle.Condition;

import lombok.Data;

import java.util.Date;

@Data
public class CarCondition {

    /**
     * 汽车牌号
     */
    private String carId;

    /**
     * 进场时间
     */
    private Integer isBooking;

}
