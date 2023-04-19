package com.rhq.parkinglotsystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.rhq.parkinglotsystem.common.ResponseResult;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.Condition.ParkingplaceCondition;
import com.rhq.parkinglotsystem.service.ParkingplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/parkingplace")
public class ParkinplaceController {
    @Autowired
    private ParkingplaceService parkingplaceService;

    /**
     * 获取车辆列表（包含条件查询）
     * @param condition 查询条件
     * @return 车辆列表
     */
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    public ResponseResult<PageInfo<Parkingplace>> FindAllParkingplaces(@RequestBody ParkingplaceCondition condition){
        List<Parkingplace> parkingplaceList = parkingplaceService.FindAllParkingplaces(condition);
        PageInfo<Parkingplace> pageInfo = new PageInfo<>(parkingplaceList);
        return parkingplaceList != null ? ResponseResult.Success(pageInfo,"成功!") : ResponseResult.Failed("失败!");
    }

    /**
     * 保存车辆
     * @param entity 车辆信息
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseResult<Integer> SaveParkingplace(@RequestBody Parkingplace entity){
        if (entity != null){
            int result = parkingplaceService.SaveParkingplace(entity);
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
    public ResponseResult<Integer> DeleteParkingplace(@RequestParam("Id") String Id){
        if (StringUtils.isNotBlank(Id)){
            int result = parkingplaceService.DeleteParkingplace(Id);
            return result != 0 ? ResponseResult.Success(result,"删除成功！"):ResponseResult.Failed("删除失败!");
        }
        else{
            return ResponseResult.Failed("删除失败!");
        }
    }

    /**
     * 搜索单个停车位
     * @param ParkingPlaceId
     * @return
     */
    @RequestMapping(value = "/selectplace",method = RequestMethod.GET)
    public Parkingplace SelectPlace(@RequestParam String ParkingPlaceId){
        Parkingplace parkingplace = parkingplaceService.SelectPlace(ParkingPlaceId);
        return parkingplace;
    }
}
