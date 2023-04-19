package com.rhq.parkinglotsystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.rhq.parkinglotsystem.common.ResponseResult;
import com.rhq.parkinglotsystem.config.WXRoleInfo;
import com.rhq.parkinglotsystem.modle.Customer;
import com.rhq.parkinglotsystem.modle.Condition.CustomerCondition;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import com.rhq.parkinglotsystem.service.CustomerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customerlogin",method = RequestMethod.GET)
    public String CustomerRegister(@RequestParam String code){
        if(code == null)
            return null;
        String jsoncustomer = customerService.CustomerRegister(code);
        return jsoncustomer;
    }

    /**
     * 用户先进场后再使用服务时，将用户openId和carId绑定
     * @param loginCustomer
     * @return
     */
    @RequestMapping(value = "/link",method = RequestMethod.POST)
    public LoginCustomer CustomerRegister(@RequestBody LoginCustomer loginCustomer){
        if (loginCustomer == null)
            return null;
        return customerService.CustomerRegister(loginCustomer);
    }

    /**
     * 获取顾客列表（包含条件查询）
     * @param condition 查询条件
     * @return 顾客列表
     */
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    public ResponseResult<PageInfo<Customer>> FindAllCustomers(@RequestBody CustomerCondition condition){
        List<Customer> customerList = customerService.FindAllCustomers(condition);
        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
        return customerList != null ? ResponseResult.Success(pageInfo,"成功!") : ResponseResult.Failed("失败!");
    }

    /**
     * 保存顾客
     * @param entity 顾客信息
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseResult<Integer> SaveCustomer(@RequestBody Customer entity){
        if (entity != null){
            int result = customerService.SaveCustomer(entity);
            return result != 0 ? ResponseResult.Success(result,"保存成功！"):ResponseResult.Failed("保存失败!");
        }else{
            return ResponseResult.Failed("无效对象，保存失败!");
        }
    }

    /**
     * 保存顾客
     * @param Id 顾客Id
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseResult<Integer> DeleteCustomer(@RequestParam String Id){
        if (StringUtils.isNotBlank(Id)){
            int result = customerService.DeleteCustomer(Id);
            return result != 0 ? ResponseResult.Success(result,"删除成功！"):ResponseResult.Failed("删除失败!");
        }
        else{
            return ResponseResult.Failed("删除失败!");
        }
    }
}
