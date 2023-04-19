package com.rhq.parkinglotsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.mapper.ParkingplaceMapper;
import com.rhq.parkinglotsystem.modle.Condition.ParkingplaceCondition;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.service.ParkingplaceService;
import com.rhq.parkinglotsystem.mapper.ParkingplaceMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_parkingplace】的数据库操作Service实现
* @createDate 2023-01-03 13:00:56
*/
@Service
public class ParkingplaceServiceImpl extends ServiceImpl<ParkingplaceMapper, Parkingplace>
    implements ParkingplaceService{
    @Autowired(required = true)
    private ParkingplaceMapper parkingplaceMapper;

    /**
     * 查询车位列表（含条件查询）
     * @param condition
     * @return
     */
    @Override
    public List<Parkingplace> FindAllParkingplaces(ParkingplaceCondition condition) {
        try{
            QueryWrapper<Parkingplace> queryWrapper = new QueryWrapper<>();
            if (condition != null){
                if (condition.getParkingPlaceId() != null)
                    queryWrapper.like("parkingPlaceId",condition.getParkingPlaceId());
                if (condition.getIsParking() != null)
                    queryWrapper.like("isParking",condition.getIsParking());
            }
            return parkingplaceMapper.selectList(queryWrapper);
        }catch (Exception ex){
            throw ex;
        }
    }


    /**
     * 保存parkingplace
     * 若不存在，则insert；
     * 若存在，则update；
     * @param entity
     * @return
     */
    @Override
    public int SaveParkingplace(Parkingplace entity) {
        try{
            if (entity == null)
                return 0;
            if (entity.getId() == null) {
                return parkingplaceMapper.insert(entity);
            } else {
                return parkingplaceMapper.updateById(entity);
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
    public int DeleteParkingplace(String Id) {
        if (StringUtils.isNotBlank(Id)){
            Parkingplace parkingplace = parkingplaceMapper.selectById(Id);
            if (parkingplace == null)
                return 0;
            return parkingplaceMapper.deleteById(Id);
        }else{
            return 0;
        }
    }

    @Transactional
    @Override
    public Parkingplace SelectPlace(String ParkingPlaceId) {
//        if (StringUtils.isAnyBlank(ParkingPlaceId)){
//            //未预约车位
//            QueryWrapper<Parkingplace> ppqw = new QueryWrapper<>();
//            ppqw.eq("isParking",0).last("limit 1");
//            Parkingplace freePlace = parkingplaceMapper.selectOne(ppqw);
//            //如果没有空余车位，则返回预约失败
//            if (freePlace == null)
//                return null;
//            //否则
//            //1.将车位置于“使用中”
//            freePlace.setIsParking(1);
//            parkingplaceMapper.updateById(freePlace);
//        }else{
//            //预约车位
//            QueryWrapper<Parkingplace> ppqw = new QueryWrapper<>();
//            ppqw.eq("parkingPlaceId",ParkingPlaceId);
//            Parkingplace selectPlace = parkingplaceMapper.selectOne(ppqw);
//            //否则
//            //1.将车位置于“使用中”
//            freePlace.setIsParking(1);
//            parkingplaceMapper.updateById(freePlace);
//        }
        return null;
    }

}




