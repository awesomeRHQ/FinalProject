package com.rhq.parkinglotsystem.modle.response;

import lombok.Data;

@Data
public class SaftyParkingLot {
    /**
     * 停车场编号
     */
    private String plId;

    /**
     * 停车场名称
     */
    private String plName;

    /**
     * 停车场坐标（纬度）
     */
    private Double plLatitude;

    /**
     * 停车场坐标（经度）
     */
    private Double plLongitude;

    /**
     * 听否停满
     */
    private Integer isFull;
}
