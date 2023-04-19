package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName t_car
 */
@TableName(value ="t_car")
@Data
public class Car implements Serializable {
    /**
     * 主键标识
     */
    @JsonProperty("Id")
    @TableId(type = IdType.AUTO)
    private Integer Id;

    /**
     * 汽车牌号
     */
    @JsonProperty("carId")
    private String carId;

    /**
     * 是否预约
     */
    private Integer isBooking;

    /**
     * 预约截至时间
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date deadline;

    /**
     * 进场时间
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date enterTime;

    /**
     * 离场时间
     */
    @TableField(updateStrategy=FieldStrategy.IGNORED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date leaveTime;

    /**
     * 停车费用
     */
    private int cost;

    /**
     * 停车时长
     */
    private float parkTime;

    /**
     * 逻辑删除：0-未删除 1-已删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 顾客表外键
     */
    @JsonProperty("openId")
    private String openId;

    /**
     * 车位表外键
     */
    private String parkingPlaceId;

    /**
     * 月卡表外键
     */
    private String cardId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}