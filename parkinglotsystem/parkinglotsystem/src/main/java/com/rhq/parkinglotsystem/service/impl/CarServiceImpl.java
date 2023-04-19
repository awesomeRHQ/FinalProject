package com.rhq.parkinglotsystem.service.impl;

import cn.hutool.core.codec.Caesar;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.common.CreateAny;
import com.rhq.parkinglotsystem.mapper.CarMapper;
import com.rhq.parkinglotsystem.mapper.ParkingplaceMapper;
import com.rhq.parkinglotsystem.modle.Car;
import com.rhq.parkinglotsystem.modle.Condition.CarCondition;
import com.rhq.parkinglotsystem.modle.Car;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.request.CarRequest;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import com.rhq.parkinglotsystem.service.CarService;
import com.rhq.parkinglotsystem.mapper.CarMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @author 20725
* @description 针对表【t_car】的数据库操作Service实现
* @createDate 2023-01-03 13:00:29
*/
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car>
    implements CarService{

    @Autowired(required = true)
    private CarMapper carMapper;

    @Autowired
    private ParkingplaceMapper parkingplaceMapper;

    //预约有效时限
    public static long BOOKING_DURATION = 1200000;

    //停车计费时间
    public static double REGULAR_TIME = 0.5;
    //停车计费规则
    public static int A_COST = 5;

    /**
     * 查询车辆列表（含条件查询）
     * @param condition
     * @return
     */
    @Override
    public List<Car> FindAllCars(CarCondition condition) {
        try{
            QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
            if (condition != null){
                if (condition.getCarId() != "")
                    queryWrapper.like("carId",condition.getCarId());
                if (condition.getIsBooking() != null)
                    queryWrapper.eq("isBooking",condition.getIsBooking());
            }
            return carMapper.selectList(queryWrapper);
        }catch (Exception ex){
            throw ex;
        }
    }


    /**
     * 保存car
     * 若不存在，则insert；
     * 若存在，则update；
     * @param entity
     * @return
     */
    @Override
    public int SaveCar(Car entity) {
        try{
            if (entity != null){
                if (StringUtils.isAnyBlank(entity.getCarId())){
                    return carMapper.insert(entity);
                }else{
                    long a = entity.getDeadline().getTime();
                    if(entity.getDeadline() != null && entity.getDeadline().getTime() == 0)
                        entity.setDeadline(null);
                    if(entity.getEnterTime() != null && entity.getEnterTime().getTime() == 0)
                        entity.setEnterTime(null);
                    if(entity.getLeaveTime() != null && entity.getLeaveTime().getTime() == 0)
                        entity.setLeaveTime(null);
                    return carMapper.updateById(entity);
                }
            }else{
                return 0;
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    /**
     * 根据Id删除车辆（逻辑删除）
     * @param Id
     * @return
     */
    @Override
    public int DeleteCar(String Id) {
        try {
            if (StringUtils.isNotBlank(Id)){
                Car car = carMapper.selectById(Id);
                if (car == null)
                    return 0;
                return carMapper.deleteById(Id);
            }else{
                return 0;
            }
        }catch (Exception e){
            throw e;
        }

    }

    @Transactional //事务
    @Override
    public LoginCustomer Booking(String CarId,String OpenId) {
        QueryWrapper<Parkingplace> ppqw = new QueryWrapper<>();
        ppqw.eq("isParking",0).last("limit 1");
        Parkingplace emptyPlace = parkingplaceMapper.selectOne(ppqw);
        //如果没有空余车位，则返回预约失败
        if (emptyPlace == null)
            return null;
        //否则
        //1.将车位置于“使用中”
        emptyPlace.setIsParking(1);
        //将更新写回数据库
        parkingplaceMapper.updateById(emptyPlace);

        QueryWrapper<Car> cqw = new QueryWrapper<>();
        //已经注册，且未进场
        cqw.eq("carId",CarId).isNull("enterTime").ne("isDelete",1);
        Car existCar = carMapper.selectOne(cqw);
        if (existCar != null){
            //将车辆预约状态变为“已预约”
            existCar.setIsBooking(1);
            //设置预约截止期限
            existCar.setDeadline(GetDeadline());
            //设置停车位
            existCar.setParkingPlaceId(emptyPlace.getParkingPlaceId());
            //更新车辆表
            carMapper.updateById(existCar);
            //返回入场车辆信息
            return SetLoginCustomer(emptyPlace,existCar);
        }else{
            //新业务车辆
            Car newCar = new Car();
            newCar.setOpenId(OpenId);
            newCar.setCarId(CarId);
            //将车辆预约状态变为“已预约”
            newCar.setIsBooking(1);
            //设置预约截止期限
            newCar.setDeadline(GetDeadline());
            //设置停车位
            newCar.setParkingPlaceId(emptyPlace.getParkingPlaceId());
            //更新车辆表
            carMapper.insert(newCar);
            Date deadline = newCar.getDeadline();
            //返回入场车辆信息
            return carMapper.SelectNewLoginCustomer(CarId, OpenId, newCar.getId());
        }
    }

    /**
     * 获取预约截止时间方法
     * @return deadline
     */
    public Date GetDeadline(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dl = timestamp.getTime() + BOOKING_DURATION;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date deadline = new Date(dl);
        return deadline;
    }

    //车辆进入停车场的方法
    @Transactional
    @Override
    public LoginCustomer EnterPark(LoginCustomer enterCar) {
        //重复入场车辆
        if (enterCar.getEnterTime() != null)
            return null;
        //判断是否存在预约信息，且预约时间有效
        if (enterCar.getDeadline() != null && CompareTime(enterCar.getDeadline())){
            //查找预约的车位
            //更新车辆入场信息
            QueryWrapper<Car> cqw = new QueryWrapper<>();
            //车辆有预约，预约时间有效，且没有进场
            //cqw.eq("carId",enterCar.getCarId()).eq("isBooking",1).isNotNull("deadline").isNull("enterTime");
            cqw.eq("id",enterCar.getId());
            Car existCar = carMapper.selectOne(cqw);
            //更新车辆入场时间
            existCar.setEnterTime(new Date());
            carMapper.updateById(existCar);
            //更新入场标记
            enterCar.setEnterTime(existCar.getEnterTime());
            return enterCar;
        }else{
            //未预约车位
            QueryWrapper<Parkingplace> ppqw = new QueryWrapper<>();
            ppqw.eq("isParking",0).last("limit 1");
            Parkingplace freePlace = parkingplaceMapper.selectOne(ppqw);
            //如果没有空余车位，则返回null
            if (freePlace == null)
                return enterCar;
            //否则 更新入场车辆信息，以及返回停车位坐标（用于导航）
            //将车位置于“使用中”
            freePlace.setIsParking(1);
            parkingplaceMapper.updateById(freePlace);
            //判断车辆信息是否已经存在
            if (enterCar.getId()!=0){
                QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
                carQueryWrapper.eq("id",enterCar.getId());
                Car existCar = carMapper.selectOne(carQueryWrapper);
                existCar.setParkingPlaceId(freePlace.getParkingPlaceId());
                existCar.setEnterTime(new Date());
                existCar.setUpdateTime(new Date());
                carMapper.updateById(existCar);
                return SetLoginCustomer(freePlace,existCar);
            }else{
                //创建新入场车辆对象
                Car newCar = new Car();
                newCar.setOpenId(enterCar.getOpenId());
                newCar.setCarId(enterCar.getCarId());
                newCar.setParkingPlaceId(freePlace.getParkingPlaceId());
                newCar.setEnterTime(new Date());
                carMapper.insert(newCar);
                //6.返回入场车辆信息
                return SetLoginCustomer(freePlace,newCar);
            }
        }
    }

    @Transactional
    @Override
    public LoginCustomer LeavePark(LoginCustomer leaveCar) {
        //重复离场车辆
        if (leaveCar.getLeaveTime() != null)
            return leaveCar;
//        if (leaveCar.getEnterTime() != null & leaveCar.getLeaveTime() == null){
            //更新车辆数据
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
//            carQueryWrapper.eq("carId",leaveCar.getCarId()).isNotNull("enterTime").isNull("leaveTime").eq("isDelete",0);
            carQueryWrapper.eq("id",leaveCar.getId());
            Car car = carMapper.selectOne(carQueryWrapper);
            //更新车辆离场时间
            car.setLeaveTime(new Date());
            //计算停车时间
            float ptFormat = (float)(car.getLeaveTime().getTime() - car.getEnterTime().getTime()) / 1000 / 3600;
            float parkTime = Float.parseFloat(String.format("%.2f",ptFormat));
            //计算停车费用
            int cost = ComputeCost(parkTime);

            //更新车辆停车费用
            car.setCost(cost);
            //更新车辆停放时间
            car.setParkTime(parkTime);
            car.setUpdateTime(new Date());
            carMapper.updateById(car);
            //更新停车位数据
            QueryWrapper<Parkingplace> parkingplaceQueryWrapper = new QueryWrapper<>();
            parkingplaceQueryWrapper.eq("parkingPlaceId",leaveCar.getParkingPlaceId());
            Parkingplace parkingplace = parkingplaceMapper.selectOne(parkingplaceQueryWrapper);
            //更新车位状态
            parkingplace.setIsParking(0);
            //更新车位总停放数
            parkingplace.setTotalCars(parkingplace.getTotalCars()+1);
            //更新车位总收费
            parkingplace.setTotalCost(parkingplace.getTotalCost()+cost);
            parkingplace.setUpdateTime(new Date());
            parkingplaceMapper.updateById(parkingplace);
            //封装返回数据
            //若月卡有效，则不需要缴费
            leaveCar.setLeaveTime(car.getLeaveTime());
            leaveCar.setParkTime(parkTime);
            if (leaveCar.getVip() != ""){
                return leaveCar;
            }
            leaveCar.setCost(cost);
            return leaveCar;
    }

    /**
     * 比较预约时间的方法
     * @param deadline
     * @return
     */
    public boolean CompareTime(Date deadline){
        long currentTime = System.currentTimeMillis();
        long d = deadline.getTime();
        if ((deadline.getTime() - currentTime) > 0 )
            return true;
        return false;
    }

    /**
     * 计算停车费的方法
     * @param parkTime
     * @return
     */
    public int ComputeCost(float parkTime){
        //小于 REGULAR_TIME 不计费
        if (parkTime < REGULAR_TIME){
            return 0;
        }else{
            //大于 REGULAR_TIME，大于部分按 REGULAR_TIME 计费
            Float x = Float.parseFloat (String.format("%.2f",(float)(parkTime - REGULAR_TIME) / REGULAR_TIME));
            if (x <= 1){
                return A_COST;
            }else{
                return x.intValue() * A_COST + A_COST;
            }
        }
    }

    public LoginCustomer SetLoginCustomer(Parkingplace place,Car car){
        LoginCustomer loginCustomer = new LoginCustomer();
        loginCustomer.setId(car.getId());
        loginCustomer.setOpenId(car.getOpenId());
        loginCustomer.setCarId(car.getCarId());
        loginCustomer.setIsBooking(1);
        loginCustomer.setDeadline(car.getDeadline());
        loginCustomer.setParkingPlaceId(car.getParkingPlaceId());
        loginCustomer.setLatitude(place.getLatitude());
        loginCustomer.setLongitude(place.getLongitude());
        loginCustomer.setEnterTime(car.getEnterTime());
        loginCustomer.setLeaveTime(car.getLeaveTime());
        return loginCustomer;
    }
}




