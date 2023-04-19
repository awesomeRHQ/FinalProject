package com.rhq.parkinglotsystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.rhq.parkinglotsystem.common.ResponseResult;
import com.rhq.parkinglotsystem.modle.Vip;
import com.rhq.parkinglotsystem.modle.Condition.VipCondition;
import com.rhq.parkinglotsystem.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private VipService vipService;

    /**
     * 获取月卡列表（包含条件查询）
     * @param condition 查询条件
     * @return 月卡列表
     */
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    public ResponseResult<PageInfo<Vip>> FindAllVips(@RequestBody VipCondition condition){
        List<Vip> vipList = vipService.FindAllVips(condition);
        PageInfo<Vip> pageInfo = new PageInfo<>(vipList);
        return vipList != null ? ResponseResult.Success(pageInfo,"成功!") : ResponseResult.Failed("失败!");
    }

    /**
     * 保存月卡
     * @param entity 月卡信息
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseResult<Integer> SaveVip(@RequestBody Vip entity){
        if (entity != null){
            int result = vipService.SaveVip(entity);
            return result != 0 ? ResponseResult.Success(result,"保存成功！"):ResponseResult.Failed("保存失败!");
        }else{
            return ResponseResult.Failed("无效对象，保存失败!");
        }
    }

    /**
     * 保存月卡
     * @param Id 月卡Id
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseResult<Integer> DeleteVip(@RequestParam String Id){
        if (StringUtils.isNotBlank(Id)){
            int result = vipService.DeleteVip(Id);
            return result != 0 ? ResponseResult.Success(result,"删除成功！"):ResponseResult.Failed("删除失败!");
        }
        else{
            return ResponseResult.Failed("删除失败!");
        }
    }
}
