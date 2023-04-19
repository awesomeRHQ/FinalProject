package com.rhq.parkinglotsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.common.CreateAny;
import com.rhq.parkinglotsystem.common.UpLoadImage;
import com.rhq.parkinglotsystem.mapper.StaffMapper;
import com.rhq.parkinglotsystem.modle.Condition.StaffCondition;
import com.rhq.parkinglotsystem.modle.Staff;
import com.rhq.parkinglotsystem.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_staff】的数据库操作Service实现
* @createDate 2023-01-03 13:01:07
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff>
    implements StaffService {

    @Autowired(required = true)
    private StaffMapper staffMapper;

    @Autowired
    private CreateAny createAny;

    /**
     * 查询职工列表（含条件查询）
     * @param condition
     * @return
     */
    @Override
    public List<Staff> FindAllStaffs(StaffCondition condition) {
        try{
            QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
            if (condition != null){
                if (condition.getStaffId() != "")
                    queryWrapper.like("staffId",condition.getStaffId());
                if (condition.getStaffName() != "")
                    queryWrapper.like("staffName",condition.getStaffName());
                if (condition.getGender() != "")
                    queryWrapper.like("gender",condition.getGender());
                if (condition.getPost() != "")
                    queryWrapper.like("post",condition.getPost());
            }
            return staffMapper.selectList(queryWrapper);
        }catch (Exception ex){
            throw ex;
        }
    }


    /**
     * 保存staff
     * 若不存在，则insert；
     * 若存在，则update；
     * @param entity
     * @return
     */
    @Override
    public int SaveStaff(Staff entity) {
        try{
            if (entity != null){
                if (StringUtils.isAnyBlank(entity.getStaffId())){
                    entity.setStaffId(createAny.CreateStaffID());
                    return staffMapper.insert(entity);
                }else{
                    Staff staff = staffMapper.selectById(entity.getID());
                    if (staff == null)
                        return 0;
                    return staffMapper.updateById(entity);
                }
            }else{
                return 0;
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    /**
     * 根据Id删除职工（逻辑删除）
     * @param Id
     * @return
     */
    @Override
    public int DeleteStaff(String Id) {
        if (StringUtils.isNotBlank(Id)){
            Staff staff = staffMapper.selectById(Id);
            if (staff == null)
                return 0;
            return staffMapper.deleteById(Id);
        }else{
            return 0;
        }

    }


}




