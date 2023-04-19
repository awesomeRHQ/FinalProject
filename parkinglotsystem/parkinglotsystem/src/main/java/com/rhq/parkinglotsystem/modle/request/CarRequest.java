package com.rhq.parkinglotsystem.modle.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarRequest {
    /**
     * 顾客表外键
     */
    @JsonProperty("OpenId")
    private String openId;

    /**
     * 汽车牌号
     */
    @JsonProperty("LicensePlateId")
    private String licensePlateId;
}
