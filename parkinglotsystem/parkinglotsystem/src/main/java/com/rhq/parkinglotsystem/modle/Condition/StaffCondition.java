package com.rhq.parkinglotsystem.modle.Condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StaffCondition {

    /**
     * 职工id（工号），职工标识
     */
    @JsonProperty("staffId")
    private String staffId;

    /**
     * 职工姓名
     */
    @JsonProperty("staffName")
    private String staffName;

    /**
     * 性别
     */
    @JsonProperty("gender")
    private String gender;

    /**
     * 岗位
     */
    @JsonProperty("post")
    private String post;
}
