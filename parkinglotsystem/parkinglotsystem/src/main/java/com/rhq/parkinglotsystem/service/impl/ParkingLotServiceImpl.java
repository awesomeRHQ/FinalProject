package com.rhq.parkinglotsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.mapper.ParkingplaceMapper;
import com.rhq.parkinglotsystem.modle.ParkingLot;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.response.SaftyParkingLot;
import com.rhq.parkinglotsystem.service.ParkingLotService;
import com.rhq.parkinglotsystem.mapper.ParkingLotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 20725
* @description 针对表【t_parkinglot】的数据库操作Service实现
* @createDate 2023-03-30 15:10:06
*/
@Service
public class ParkingLotServiceImpl extends ServiceImpl<ParkingLotMapper, ParkingLot>
    implements ParkingLotService{

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    public List<SaftyParkingLot> GetList() {
        try{
            QueryWrapper<ParkingLot> parkingLotQueryWrapper = new QueryWrapper<>();
            parkingLotQueryWrapper.eq("isFull",0);
            List<ParkingLot> parkinglots = parkingLotMapper.selectList(parkingLotQueryWrapper);
            List<SaftyParkingLot> saftyParkingLots = new ArrayList<>();
            for (ParkingLot pl : parkinglots) {
                SaftyParkingLot saftyParkingLot = new SaftyParkingLot();
                saftyParkingLot.setPlId(pl.getPlId());
                saftyParkingLot.setPlName(pl.getPlName());
                saftyParkingLot.setPlLatitude(pl.getPlLatitude());
                saftyParkingLot.setPlLongitude(pl.getPlLongitude());
                saftyParkingLot.setIsFull(pl.getIsFull());
                saftyParkingLots.add(saftyParkingLot);
            }
            return saftyParkingLots;
        }catch (Exception e){
            throw e;
        }
    }
}




