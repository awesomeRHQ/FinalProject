package com.rhq.parkinglotsystem.mapper;

import com.rhq.parkinglotsystem.modle.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
* @author 20725
* @description 针对表【t_car】的数据库操作Mapper
* @createDate 2023-01-03 13:00:29
* @Entity geno.modle.Car
*/
public interface CarMapper extends BaseMapper<Car> {

    @Select("select tc.id,tc.openId,tc.carId,tc.isBooking,tc.deadline,tp.parkingPlaceId,tp.latitude,tp.longitude,tv.cardId vip,tv.endTime vipDeadline " +
            "from t_car tc " +
            "left join t_vip tv on tc.openId = tv.customerId " +
            "left join t_parkingplace tp on tc.parkingPlaceId = tp.parkingPlaceId " +
            "where tc.openId = #{OpenId} and tc.CarId = #{CarId} and tc.id = #{Id}")
    LoginCustomer SelectNewLoginCustomer(String CarId, String OpenId, Integer Id);
}




