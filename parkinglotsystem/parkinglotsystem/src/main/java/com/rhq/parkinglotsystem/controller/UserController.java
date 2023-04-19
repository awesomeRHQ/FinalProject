package com.rhq.parkinglotsystem.controller;

import com.github.pagehelper.PageInfo;
import com.rhq.parkinglotsystem.common.ResponseResult;
import com.rhq.parkinglotsystem.modle.Condition.StaffCondition;
import com.rhq.parkinglotsystem.modle.Condition.UserCondition;
import com.rhq.parkinglotsystem.modle.Staff;
import com.rhq.parkinglotsystem.modle.User;
import com.rhq.parkinglotsystem.modle.request.LoginRequest;
import com.rhq.parkinglotsystem.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 管理员为员工用户注册
     * @return
     */
    @RequestMapping("/workerRegister")
    public Long workerRegister(){
        return null;
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<User> userLogin(@RequestBody LoginRequest request){
        String userAccount = request.getUserAccount();
        String password = request.getPassword();
        if(userAccount == null || password == null){
            return null;
        }
        if(StringUtils.isAnyBlank(userAccount,password)){
            return null;
        }
        User loginUser = userService.UserLogin(userAccount, password);
        return loginUser != null ? ResponseResult.Success(loginUser,"登录成功!") : ResponseResult.Failed("登录失败!");
    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/register")
    public ResponseResult<Integer> userRegister(@RequestBody User user){
        int res = userService.UserRegister(user);
        return res != 0 ? ResponseResult.Success(res,"注册成功!") : ResponseResult.Failed("注册失败!");
    }

    /**
     * 获取用户列表（包含条件查询）
     * @param condition 查询条件
     * @return 职工列表
     */
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    public ResponseResult<PageInfo<User>> findAllStaffs(@RequestBody UserCondition condition){
        List<User> userList = userService.FindAllUsers(condition);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return userList != null ? ResponseResult.Success(pageInfo,"成功!") : ResponseResult.Failed("失败!");
    }

    /**
     * 保存用户
     * @param entity 职工信息
     * @return 数据库影响条数
     */
//    @RequestMapping(value = "/save",method = RequestMethod.POST)
//    public ResponseResult<Integer> SaveUser(@RequestBody User entity){
//        if (entity != null){
//            int result = userService.SaveUser(entity);
//            return result != 0 ? ResponseResult.Success(result,"保存成功！"):ResponseResult.Failed("保存失败!");
//        }else{
//            return ResponseResult.Failed("无效对象，保存失败!");
//        }
//    }

    /**
     * 删除用户(逻辑删除)
     * @param Id 用户Id
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseResult<Integer> DeleteStaff(@RequestParam String Id){
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(Id)){
            int result = userService.DeleteUser(Id);
            return result != 0 ? ResponseResult.Success(result,"删除成功！"):ResponseResult.Failed("删除失败!");
        }
        else{
            return ResponseResult.Failed("删除失败!");
        }
    }
}
