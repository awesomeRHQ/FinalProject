package com.rhq.parkinglotsystem.common;

import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class CreateAny {

    public static String USER_ID_PERFIX = "U";
    /**
     * 员工工号前缀
     */
    public static String STAFF_ID_PERFIX = "STA";

    /**
     * 月卡卡号前缀
     */
    public static String VIP_ID_PERFIX = "VIP";

    /**
     * 构造用户账号
     * @return
     */
    public String CreateUserID(){
        String NewId = USER_ID_PERFIX + GetNum();
        return NewId;
    }
    /**
     * 构造员工工号
     * @return
     */
    public String CreateStaffID(){
        String NewId = STAFF_ID_PERFIX + GetNum();
        return NewId;
    }

    /**
     * 构造月卡卡号
     * @return
     */
    public String CreateVipID(){
        String NewId = VIP_ID_PERFIX + GetNum();
        return NewId;
    }

    /**
     * 构造卡号号码
     * @return
     */
    public StringBuilder GetNum(){
        StringBuilder IdPostfix = new StringBuilder();
        //获取获取系统的当前日历对象
        Calendar instance = Calendar.getInstance();
        //获取系统当前年
        IdPostfix.append(instance.get(Calendar.YEAR)+"");
        //获取系统当前月
        IdPostfix.append((instance.get(Calendar.MONTH)+"").length()==1 ? "0"+instance.get(Calendar.MONTH)+"" : instance.get(Calendar.MONTH)+"");
        //生成随机数
        IdPostfix.append((int)(Math.random() * 10000) + "");
        return IdPostfix;
    }
}
