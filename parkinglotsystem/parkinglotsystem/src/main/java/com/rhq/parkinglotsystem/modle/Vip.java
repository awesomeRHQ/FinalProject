package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @TableName t_vip
 */
@TableName(value ="t_vip")
@Data
@ToString
public class Vip implements Serializable {
    /**
     * 主键标识
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    /**
     * 月卡卡号
     */
    @JsonProperty("CardId")
    private String cardId;

    /**
     * 开卡日期
     */
    @JsonProperty("StartTime")
    private Date startTime;

    /**
     * 开卡日期
     */
    @JsonProperty("EndTime")
    private Date endTime;

    /**
     * 车牌号
     */
//    @JsonProperty("CarId")
//    private String carId;

    /**
     * 开卡人
     */
    @JsonProperty("CustomerId")
    private String customerId;

    /**
     * 逻辑删除：1-删除 0-未删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}