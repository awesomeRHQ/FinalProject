package com.rhq.parkinglotsystem.modle.Condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParkingplaceCondition {

    /**
     * 车位号
     */
    @JsonProperty("ParkingPlaceId")
    private String parkingPlaceId;


    /**
     * 是否被停放:0-空闲 1-有车停放
     */
    @JsonProperty("IsParking")
    private Integer isParking;
}
