package com.rhq.parkinglotsystem.modle.request;

import lombok.Data;

import java.util.Date;

@Data
public class staffre {
    /**
     * 职工id（工号），职工标识
     */
    private String StaffId;

    /**
     * 职工姓名
     */
    private String StaffName;

    /**
     * 性别
     */
    private String Gender;

    /**
     * 年龄
     */
    private int age;

    /**
     * 身份证号码
     */
    private String IDCard;

    /**
     * 照片
     */
    private String Photo;

    /**
     * 岗位
     */
    private String Post;

    /**
     * 创建时间
     */
    private Date CreateTime;
}
