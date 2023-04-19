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
 * @TableName t_customer
 */
@TableName(value ="t_customer")
@Data
public class Customer implements Serializable {
    /**
     * 主键标识
     */
    @TableId(type = IdType.AUTO)
    private Integer Id;

    /**
     * 顾客openId
     */
    private String openId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除:0-未删除 1-已删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}