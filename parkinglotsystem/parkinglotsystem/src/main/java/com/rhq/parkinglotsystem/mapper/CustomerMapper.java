package com.rhq.parkinglotsystem.mapper;

import com.rhq.parkinglotsystem.modle.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import org.apache.ibatis.annotations.Select;

/**
* @author 20725
* @description 针对表【t_customer】的数据库操作Mapper
* @createDate 2023-01-03 13:00:39
* @Entity geno.modle.Customer
*/
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select("SELECT tc.id,tc.openId,tc.carId,tc.isBooking,tc.deadline,tc.enterTime,tc.leaveTime,tc.parkingPlaceId,tp.longitude,tp.latitude,tc.createTime,tv.cardId vip,tv.endTime vipDeadline\n" +
            "FROM t_car tc\n" +
            "left join t_parkingplace tp\n" +
            "on tc.parkingPlaceId = tp.parkingPlaceId\n " +
            "left join t_vip tv\n" +
            "on tc.openId = tv.customerId\n" +
            "where openId = #{openID} and tc.isDelete = 0 and leaveTime is null\n" +
            "order by createTime desc limit 1;")
    LoginCustomer getLoginCustomer(String openId);

    @Select("SELECT tc.openId,tv.cardId vip,tv.endTime vipDeadline\n" +
            "FROM t_customer tc\n" +
            "left join t_vip tv\n" +
            "on tc.openId = tv.customerId\n" +
            "where openId = #{openID} and tc.isDelete = 0")
    LoginCustomer getLoginCustomer_(String openId);
}




