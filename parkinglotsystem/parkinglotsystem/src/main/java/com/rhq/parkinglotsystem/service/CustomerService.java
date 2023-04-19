package com.rhq.parkinglotsystem.service;

import com.rhq.parkinglotsystem.modle.Customer;
import com.rhq.parkinglotsystem.modle.Condition.CustomerCondition;
import com.rhq.parkinglotsystem.modle.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;

import java.util.List;

/**
* @author 20725
* @description 针对表【t_customer】的数据库操作Service
* @createDate 2023-01-03 13:00:39
*/
public interface CustomerService extends IService<Customer> {
    /**
     * 获取顾客列表
     * @param condition
     * @return
     */
    List<Customer> FindAllCustomers(CustomerCondition condition);

    /**
     * 保存顾客
     * @param entity
     * @return
     */
    int SaveCustomer(Customer entity);

    /**
     * 删除顾客
     * @param Id
     * @return
     */
    int DeleteCustomer(String Id);

    /**
     * 获取用户openid
     * @param code
     * @return
     */
    String GetUserOpenId(String code);

    /**
     * 用户登录
     * @param code
     * @return
     */
    String CustomerRegister(String code);

    /**
     * 用户和车辆绑定
     * @param loginCustomer
     * @return
     */
    LoginCustomer CustomerRegister(LoginCustomer loginCustomer);
}
