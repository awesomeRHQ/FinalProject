package com.rhq.parkinglotsystem.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.rhq.parkinglotsystem.modle.Condition.StaffCondition;
import com.rhq.parkinglotsystem.modle.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_staff】的数据库操作Service
* @createDate 2023-01-03 13:01:07
*/
@Service
public interface StaffService extends IService<Staff> {

    /**
     * 获取职工列表
     * @param condition
     * @return
     */
    List<Staff> FindAllStaffs(StaffCondition condition);

    /**
     * 保存职工
     * @param entity
     * @return
     */
    int SaveStaff(Staff entity);

    /**
     * 删除职工
     * @param Id
     * @return
     */
    int DeleteStaff(String Id);
}
