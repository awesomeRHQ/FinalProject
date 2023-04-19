package com.rhq.parkinglotsystem.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhq.parkinglotsystem.mapper.CarMapper;
import com.rhq.parkinglotsystem.mapper.ParkingplaceMapper;
import com.rhq.parkinglotsystem.mapper.VipMapper;
import com.rhq.parkinglotsystem.modle.Car;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleAdjust {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ParkingplaceMapper parkingplaceMapper;

    @Autowired
    private VipMapper vipMapper;

    @Scheduled(fixedDelay = 5000)
    public void AdjustVip(){
        try{
            QueryWrapper<Vip> vqw = new QueryWrapper<>();
            vqw.eq("isDelete",0);
            List<Vip> vipList = vipMapper.selectList(vqw);
            for ( Vip vip : vipList ) {
                long deadline = vip.getEndTime().getTime();
                if (CompareTime(deadline)){
                    vip.setIsDelete(1);
                    vipMapper.updateById(vip);
                    System.out.println("Vip: " + vip.getCardId());
                }
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String dateString = simpleDateFormat.format(date);
            System.out.println("月卡: " + dateString + " 修正");
        }catch (Exception e){
            throw e;
        }

    }

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void AdjustBooking(){
        QueryWrapper<Car> cqw = new QueryWrapper<>();
        //已预约未进场的车辆
        cqw.eq("isBooking",1).isNull("enterTime");
        List<Car> carList = carMapper.selectList(cqw);
        List<String> ppIdList = new ArrayList<>();
        for (Car car:carList) {
            long deadline = car.getDeadline().getTime();
            //预约已过期
            if (CompareTime(deadline)){
                ppIdList.add(car.getParkingPlaceId());
                AdjustCarBooking(car);
            }
        }
        AdjustParkingPlace(ppIdList);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        System.out.println("车位预约: " + dateString + " 修正");
    }

    Boolean CompareTime(long deadline){
        long currentTime = new Date().getTime();
        if (deadline - currentTime < 0)
            //超过时间
            return true;
        else
            return false;
    }

    /**
     * 修正车辆预约状态
     * @param car
     */
    void AdjustCarBooking(Car car){
        QueryWrapper<Car> cqw = new QueryWrapper<>();
        cqw.eq("id",car.getId());
        Car existCar = carMapper.selectOne(cqw);
        existCar.setIsBooking(0);
        existCar.setParkingPlaceId("");
        carMapper.updateById(existCar);
        System.out.println("Car:");
        System.out.println(car.getId()+":"+car.getCarId());
    }

    /**
     * 修正停车位状态
     * @param ppIdList
     */
    void AdjustParkingPlace(List<String> ppIdList){
        QueryWrapper<Parkingplace> ppqw = new QueryWrapper<>();
        for (String ppid: ppIdList) {
            ppqw.eq("parkingPlaceId",ppid);
            Parkingplace parkingplace = parkingplaceMapper.selectOne(ppqw);
            parkingplace.setIsParking(0);
            parkingplaceMapper.updateById(parkingplace);
            System.out.println("ParkingPlace:");
            System.out.println(parkingplace.getParkingPlaceId());
        }
    }
}
