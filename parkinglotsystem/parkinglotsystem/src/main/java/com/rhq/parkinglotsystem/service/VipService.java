package com.rhq.parkinglotsystem.service;

import com.rhq.parkinglotsystem.modle.Vip;
import com.rhq.parkinglotsystem.modle.Condition.VipCondition;
import com.rhq.parkinglotsystem.modle.Vip;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_vip】的数据库操作Service
* @createDate 2023-01-03 13:01:25
*/
public interface VipService extends IService<Vip> {
    /**
     * 获取月卡列表
     * @param condition
     * @return
     */
    List<Vip> FindAllVips(VipCondition condition);

    /**
     * 保存月卡
     * @param entity
     * @return
     */
    int SaveVip(Vip entity);

    /**
     * 删除月卡
     * @param Id
     * @return
     */
    int DeleteVip(String Id);
}
