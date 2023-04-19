package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName log_login
 */
@TableName(value ="log_login")
@Data
public class LogLogin implements Serializable {
    /**
     * 主键标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登出时间
     */
    private Date outLoginTime;

    /**
     * 登录客户端ip
     */
    private String loginIp;

    /**
     * 是否成功
     */
    private Integer isSuccess;

    /**
     * 职工表外键，约束：cascade
     */
    private String staffId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}