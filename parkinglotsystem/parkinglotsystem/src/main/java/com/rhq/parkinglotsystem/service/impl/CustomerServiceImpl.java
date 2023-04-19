package com.rhq.parkinglotsystem.service.impl;

import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.config.WXRoleInfo;
import com.rhq.parkinglotsystem.mapper.CarMapper;
import com.rhq.parkinglotsystem.mapper.CustomerMapper;
import com.rhq.parkinglotsystem.modle.Car;
import com.rhq.parkinglotsystem.modle.Customer;
import com.rhq.parkinglotsystem.modle.Condition.CustomerCondition;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import com.rhq.parkinglotsystem.modle.response.WXUserKey;
import com.rhq.parkinglotsystem.service.CustomerService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
* @author 20725
* @description 针对表【t_customer】的数据库操作Service实现
* @createDate 2023-01-03 13:00:39
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{

    @Autowired(required = true)
    private CustomerMapper customerMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private WXRoleInfo wxrole;

    /**
     * 查询车辆列表（含条件查询）
     * @param condition
     * @return
     */
    @Override
    public List<Customer> FindAllCustomers(CustomerCondition condition) {
        try{
            QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
            if (condition != null){
                if (condition.getCreateTime() != null)
                    queryWrapper.like("createTime",condition.getCreateTime());
                if(condition.getOpenId() != null)
                    queryWrapper.like("openid",condition.getOpenId());
            }
            return customerMapper.selectList(queryWrapper);
        }catch (Exception ex){
            throw ex;
        }
    }


    /**
     * 保存customer
     * 若不存在，则insert；
     * 若存在，则update；
     * @param entity
     * @return
     */
    @Override
    public int SaveCustomer(Customer entity) {
        try{
            if (entity == null)
                return 0;
            if (!StringUtils.isAnyBlank(entity.getOpenId())){
                return customerMapper.insert(entity);
            }else{
                Customer customer = customerMapper.selectById(entity.getId());
                if (customer == null)
                    return 0;
                return customerMapper.updateById(entity);
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
    public int DeleteCustomer(String Id) {
        try {
            if (StringUtils.isNotBlank(Id)){
                Customer customer = customerMapper.selectById(Id);
                if (customer == null)
                    return 0;
                return customerMapper.deleteById(Id);
            }else{
                return 0;
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public String CustomerRegister(String code) {
        try{
            if (code == null)
                return null;
            String openId = GetUserOpenId(code);
            QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
            //根据登录的openId查找用户
            queryWrapper.eq("openId",openId).orderByDesc("createTime").last("limit 1");
            Customer existCustomer = customerMapper.selectOne(queryWrapper);
            //用户存在
            if (existCustomer != null){
                //在car表中查询是否存在最新的未入场的车辆信息
                LoginCustomer loginCustomer = customerMapper.getLoginCustomer(openId);
                //用户只有登录，没有其他动作，则只返回用户信息
                if(loginCustomer == null){
                    LoginCustomer newCustomer = customerMapper.getLoginCustomer_(openId);
                    return JSONObject.fromObject(newCustomer).toString();
                }
                return JSONObject.fromObject(loginCustomer).toString();
            }
            //若customer不存在则在customer表中新增
            Customer newCustomer = new Customer();
            newCustomer.setOpenId(openId);
            SaveCustomer(newCustomer);
            //返回LoginCustomer对象
            LoginCustomer registerCustomer = customerMapper.getLoginCustomer_(openId);
            return JSONObject.fromObject(registerCustomer).toString();
        }catch(Exception e){
            throw e;
        }

    }

    /**
     * 获取用户的OpenId
     * @param code
     * @return
     */
    @Override
    public String GetUserOpenId(String code) {
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            HashMap<String, Object> params = new HashMap<>();
            params.put("appid",wxrole.APPID);
            params.put("secret",wxrole.SECRET);
            params.put("js_code",code);
            params.put("grant_type",wxrole.GRANT_TYPE);
            //发出get请求,获取OpenId
            String res = HttpUtil.get(url, params);
            //通过fromObject将json字符串翻译成JSON对象(JSONObject)
            JSONObject jsonObject = JSONObject.fromObject(res);
            String openid = jsonObject.getString("openid");
            return openid;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoginCustomer CustomerRegister(LoginCustomer loginCustomer){
        try{
            QueryWrapper<Car> cqw = new QueryWrapper<>();
            //判断是修改车牌还是绑定车牌
            if(loginCustomer.getId() != 0){
                cqw.eq("id",loginCustomer.getId());
                Car existCar = carMapper.selectOne(cqw);
                existCar.setCarId(loginCustomer.getCarId());
                carMapper.updateById(existCar);
                return loginCustomer;
            }else{
                cqw.eq("carId",loginCustomer.getCarId()).isNull("openId").isNotNull("enterTime").isNull("leaveTime");
                Car existCar = carMapper.selectOne(cqw);
                //如果不存在已入场车辆，则返回null
                if (existCar == null)
                    return loginCustomer;
                //若存在，则于用户进行绑定
                existCar.setOpenId(loginCustomer.getOpenId());
                carMapper.updateById(existCar);
                loginCustomer.setCarId(existCar.getCarId());
                loginCustomer.setId(existCar.getId());
                loginCustomer.setEnterTime(existCar.getEnterTime());
                return loginCustomer;
            }
        }catch (Exception ex){
            throw ex;
        }
    }

}




