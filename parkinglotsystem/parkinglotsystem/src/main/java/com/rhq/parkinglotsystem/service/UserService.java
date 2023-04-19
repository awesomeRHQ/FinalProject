package com.rhq.parkinglotsystem.service;

import com.rhq.parkinglotsystem.modle.Condition.StaffCondition;
import com.rhq.parkinglotsystem.modle.Condition.UserCondition;
import com.rhq.parkinglotsystem.modle.Staff;
import com.rhq.parkinglotsystem.modle.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 20725
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-01-03 12:58:57
*/
@Service
public interface UserService extends IService<User> {


     String SALT = "RHQ";

     String USER_LOGIN_STATUS = "userLoginStatus";

    /**
     * 用户注册、更新
     * @param entity
     * @return
     */
    int UserRegister(User entity);

    /**
     * 用户登录的方法
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    User UserLogin(String userAccount, String userPassword);

    /**
     * 获取脱敏用户
     * @param originUser
     * @return
     */
    User GetSafetyUser(User originUser);

    /**
     * 获取用户列表
     * @param condition
     * @return
     */
    List<User> FindAllUsers(UserCondition condition);

    /**
     * 删除用户
     * @param Tag
     * @return
     */
    int DeleteUser(String Tag);
}
