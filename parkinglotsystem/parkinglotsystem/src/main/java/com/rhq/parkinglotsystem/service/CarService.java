package com.rhq.parkinglotsystem.service;

import com.rhq.parkinglotsystem.modle.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rhq.parkinglotsystem.modle.Condition.CarCondition;
import com.rhq.parkinglotsystem.modle.Car;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.request.CarRequest;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_car】的数据库操作Service
* @createDate 2023-01-03 13:00:29
*/
public interface CarService extends IService<Car> {

    /**
     * 获取车辆列表
     * @param condition
     * @return
     */
    List<Car> FindAllCars(CarCondition condition);

    /**
     * 保存车辆
     * @param entity
     * @return
     */
    int SaveCar(Car entity);

    /**
     * 删除车辆
     * @param Id
     * @return
     */
    int DeleteCar(String Id);

    /**
     * 车位预约
     * @return
     */
    LoginCustomer Booking(String CarId,String OpenId);

    /**
     * 搜索停车位的方法
     * @param enterCar
     * @return
     */
    LoginCustomer EnterPark(LoginCustomer enterCar);

    /**
     * 离开停车场的方法
     * @param leaveCar
     * @return
     */
    LoginCustomer LeavePark(LoginCustomer leaveCar);
}
