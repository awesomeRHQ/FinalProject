package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 主键标识
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("ID")
    private Integer id;

    /**
     * 用户账号
     */
    @JsonProperty("UserAccount")
    private String userAccount;

    /**
     * 用户密码
     */
    @JsonProperty("UserPassword")
    private String userPassword;

    /**
     * 用户权限： 0-管理员 1-普通用户
     */
    @JsonProperty("UserRole")
    private Integer userRole;

    /**
     * 创建时间
     */
    @JsonProperty("CreateTime")
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除：0-未删除 1-删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 职工表外键，约束：cascade
     */
    @JsonProperty("StaffId")
    private String staffId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}