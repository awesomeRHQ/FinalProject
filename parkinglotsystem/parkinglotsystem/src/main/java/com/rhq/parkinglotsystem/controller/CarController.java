package com.rhq.parkinglotsystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.rhq.parkinglotsystem.common.ResponseResult;
import com.rhq.parkinglotsystem.modle.Condition.CarCondition;
import com.rhq.parkinglotsystem.modle.Car;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.request.CarRequest;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import com.rhq.parkinglotsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 获取车辆列表（包含条件查询）
     * @param condition 查询条件
     * @return 车辆列表
     */
    @PostMapping (value = "/getlist")
    public ResponseResult<PageInfo<Car>> FindAllCars(@RequestBody CarCondition condition){
        List<Car> carList = carService.FindAllCars(condition);
        PageInfo<Car> pageInfo = new PageInfo<>(carList);
        return carList != null ? ResponseResult.Success(pageInfo,"成功!") : ResponseResult.Failed("失败!");
    }

    /**
     * 保存车辆
     * @param entity 车辆信息
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseResult<Integer> SaveCar(@RequestBody Car entity){
        if (entity != null){
            int result = carService.SaveCar(entity);
            return result != 0 ? ResponseResult.Success(result,"保存成功！"):ResponseResult.Failed("保存失败!");
        }else{
            return ResponseResult.Failed("无效对象，保存失败!");
        }
    }

    /**
     * 保存车辆
     * @param Id 车辆Id
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseResult<Integer> DeleteCar(@RequestParam String Id){
        if (StringUtils.isNotBlank(Id)){
            int result = carService.DeleteCar(Id);
            return result != 0 ? ResponseResult.Success(result,"删除成功！"):ResponseResult.Failed("删除失败!");
        }
        else{
            return ResponseResult.Failed("删除失败!");
        }
    }

    /**
     * 车辆预约
     * @param CarId
     * @param OpenId
     * @return
     */
    @RequestMapping(value = "/booking",method = RequestMethod.GET)
    public LoginCustomer Booking(@RequestParam("CarId") String CarId,@RequestParam("OpenId") String OpenId){
        LoginCustomer loginCustomer = carService.Booking(CarId,OpenId);
        return loginCustomer;
    }

    /**
     * 车辆预约
     * @param enterCar
     * @return
     */
    @RequestMapping(value = "/enter",method = RequestMethod.POST)
    public LoginCustomer EnterPark(@RequestBody LoginCustomer enterCar){
        LoginCustomer loginCustomer = carService.EnterPark(enterCar);
        return loginCustomer;
    }

    /**
     * 车辆离场
     * @param leaveCar
     * @return
     */
    @RequestMapping(value = "/leave",method = RequestMethod.POST)
    public LoginCustomer LeavePark(@RequestBody LoginCustomer leaveCar){
        LoginCustomer loginCustomer = carService.LeavePark(leaveCar);
        return loginCustomer;
    }
}
