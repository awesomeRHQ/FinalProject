package com.rhq.parkinglotsystem.modle.Condition;

import lombok.Data;

@Data
public class UserCondition {

    /**
     * 账号
     */
    public String userAccount;

    /**
     * 职工工号
     */
    public String staffId;

    /**
     * 用户权限
     */
    public String userRole;
}
