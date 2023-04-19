package com.rhq.parkinglotsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.common.CreateAny;
import com.rhq.parkinglotsystem.mapper.VipMapper;
import com.rhq.parkinglotsystem.mapper.VipMapper;
import com.rhq.parkinglotsystem.modle.Condition.VipCondition;
import com.rhq.parkinglotsystem.modle.Vip;
import com.rhq.parkinglotsystem.modle.Vip;
import com.rhq.parkinglotsystem.service.VipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_vip】的数据库操作Service实现
* @createDate 2023-01-03 13:01:25
*/
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip>
    implements VipService{

    @Autowired(required = true)
    private VipMapper vipMapper;

    @Autowired
    private CreateAny createAny;
    /**
     * 查询月卡列表（含条件查询）
     * @param condition
     * @return
     */
    @Override
    public List<Vip> FindAllVips(VipCondition condition) {
        try{
            QueryWrapper<Vip> queryWrapper = new QueryWrapper<>();
            if (condition != null){
                if (condition.getCardId() != null)
                    queryWrapper.like("cardId",condition.getCardId());
                if (condition.getStartTime() != null)
                    queryWrapper.like("startTime",condition.getStartTime());
            }
            return vipMapper.selectList(queryWrapper);
        }catch (Exception ex){
            throw ex;
        }
    }


    /**
     * 保存vip
     * 若不存在，则insert；
     * 若存在，则update；
     * @param entity
     * @return
     */
    @Override
    public int SaveVip(Vip entity) {
        try {
            if (entity == null)
                return 0;
            if (StringUtils.isAnyBlank(entity.getCardId())){
                entity.setCardId(createAny.CreateVipID());
                return vipMapper.insert(entity);
            }else{
                Vip vip = vipMapper.selectById(entity.getId());
                if (vip == null)
                    return 0;
                return vipMapper.updateById(entity);
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    /**
     * 根据Id删除月卡（逻辑删除）
     * @param Id
     * @return
     */
    @Override
    public int DeleteVip(String Id) {
        if (StringUtils.isNotBlank(Id)){
            Vip vip = vipMapper.selectById(Id);
            if (vip == null)
                return 0;
            return vipMapper.deleteById(Id);
        }else{
            return 0;
        }
    }

}




