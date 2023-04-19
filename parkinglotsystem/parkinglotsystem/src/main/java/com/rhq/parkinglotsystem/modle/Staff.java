package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName t_staff
 */
@TableName(value ="t_staff")
@Data
public class Staff implements Serializable {
    /**
     * 主键标识
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("ID")
    private Integer ID;

    /**
     * 职工id（工号），职工标识
     */
    @JsonProperty("StaffId")
    private String StaffId;

    /**
     * 职工姓名
     */
    @JsonProperty("StaffName")
    private String StaffName;

    /**
     * 性别
     */
    @JsonProperty("Gender")
    private String Gender;

    /**
     * 年龄
     */
    @JsonProperty("Age")
    private int Age;

    /**
     * 身份证号码
     */
    @JsonProperty("IDCard")
    private String IDCard;

    /**
     * 照片
     */
    @JsonProperty("Photo")
    private String Photo;

    /**
     * 岗位
     */
    @JsonProperty("Post")
    private String Post;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 修改时间
     */
    private Date UpdateTime;

    /**
     * 创建时间
     */
    @JsonProperty("CreateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date CreateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}