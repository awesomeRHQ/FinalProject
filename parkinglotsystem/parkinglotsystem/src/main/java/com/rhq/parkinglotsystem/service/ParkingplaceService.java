package com.rhq.parkinglotsystem.service;

import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.Condition.ParkingplaceCondition;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_parkingplace】的数据库操作Service
* @createDate 2023-01-03 13:00:56
*/
public interface ParkingplaceService extends IService<Parkingplace> {

    /**
     * 获取停车位列表
     * @param condition
     * @return
     */
    List<Parkingplace> FindAllParkingplaces(ParkingplaceCondition condition);

    /**
     * 保存停车位
     * @param entity
     * @return
     */
    int SaveParkingplace(Parkingplace entity);

    /**
     * 删除停车位
     * @param Id
     * @return
     */
    int DeleteParkingplace(String Id);

    /**
     * 搜索停车位的方法
     * @param ParkingPlaceId
     * @return
     */
    Parkingplace SelectPlace(String ParkingPlaceId);
}
