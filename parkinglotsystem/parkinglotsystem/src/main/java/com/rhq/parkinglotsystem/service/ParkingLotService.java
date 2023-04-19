package com.rhq.parkinglotsystem.service;

import com.rhq.parkinglotsystem.modle.ParkingLot;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rhq.parkinglotsystem.modle.response.SaftyParkingLot;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_parkinglot】的数据库操作Service
* @createDate 2023-03-30 15:10:06
*/
public interface ParkingLotService extends IService<ParkingLot> {
    public List<SaftyParkingLot> GetList();
}
