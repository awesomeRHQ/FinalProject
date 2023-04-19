package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName t_parkingplace
 */
@TableName(value ="t_parkingplace")
@Data
public class Parkingplace implements Serializable {
    /**
     * 主键标识
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    /**
     * 车位号
     */
    @JsonProperty("ParkingPlaceId")
    private String parkingPlaceId;

    /**
     * 车位坐标（经度）
     */
    @JsonProperty("Longitude")
    private Double longitude;

    /**
     * 车位坐标（纬度）
     */
    @JsonProperty("Latitude")
    private Double latitude;

    /**
     * 是否被停放:0-空闲 1-有车停放
     */
    @JsonProperty("IsParking")
    private Integer isParking;

    /**
     * 累计收费
     */
    @JsonProperty("TotalCost")
    private Integer totalCost;

    /**
     * 总停放车辆
     */
    @JsonProperty("TotalCars")
    private Integer totalCars;

    /**
     * 修改时间
     */
    private Date UpdateTime;

    /**
     * 创建时间
     */
    @JsonProperty("CreateTime")
    private Date CreateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}