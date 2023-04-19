package com.rhq.parkinglotsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.common.CreateAny;
import com.rhq.parkinglotsystem.mapper.StaffMapper;
import com.rhq.parkinglotsystem.mapper.UserMapper;
import com.rhq.parkinglotsystem.modle.Condition.StaffCondition;
import com.rhq.parkinglotsystem.modle.Condition.UserCondition;
import com.rhq.parkinglotsystem.modle.Staff;
import com.rhq.parkinglotsystem.modle.User;
import com.rhq.parkinglotsystem.service.UserService;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author 20725
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-01-03 12:58:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired(required = true)
    private UserMapper userMapper;

    @Autowired
    private CreateAny createAny;

    @Override
    public User UserLogin(String userAccount, String userPassword) {
        if(userAccount.length() == 0 || userPassword.length() == 0){
            return null;
        }
        //验证账户是否包含特殊字符
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(regEx).matcher(userAccount);
        if (matcher.find()){
            return null;
        }
        //与数据库中的密码进行对比
        //spring自带工具类加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User querryUser = userMapper.selectOne(queryWrapper);
        if (querryUser== null)
            return null;
        if (!querryUser.getUserPassword().equals(encryptPassword)){
            return null;
        }
        User safetyUser = GetSafetyUser(querryUser);
        //request.getSession().setAttribute(USER_LOGIN_STATUS,safetyUser);
        return safetyUser;
    }

    @Override
    public User GetSafetyUser(User originUser) {
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setStaffId(originUser.getStaffId());
        return safetyUser;
    }

    /**
     * 获取用户列表（包含条件查询）
     * @param condition 查询条件
     * @return 职工列表QueryWrapper<User> queryWrapper = new QueryWrapper<>();
     *             if (condition != null){
     *                 if (condition.getUserAccount() != null)
     *                     queryWrapper.like("userAccount",condition.getUserAccount());
     *                 if (condition.getStaffId() != null)
     *                     queryWrapper.like("staffId",condition.getStaffId());
     *                 if (condition.getUserRole() != null)
     *                     queryWrapper.like("userRole",condition.getUserRole());
     *             }
     *             return userMapper.selectList(queryWrapper);
     */
    @Override
    public List<User> FindAllUsers(UserCondition condition) {
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if (condition != null){
                if (condition.getUserAccount() != null)
                    queryWrapper.like("userAccount",condition.getUserAccount());
                if (condition.getStaffId() != null)
                    queryWrapper.like("staffId",condition.getStaffId());
                if (condition.getUserRole() != null)
                    queryWrapper.like("userRole",condition.getUserRole());
            }
            return userMapper.selectList(queryWrapper);
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
    public int UserRegister(User entity) {
        try{
            if (entity != null){
                if (StringUtils.isAnyBlank(entity.getUserAccount())){
                    entity.setUserAccount(createAny.CreateUserID());
                    entity.setUserPassword(DigestUtils.md5DigestAsHex((SALT + entity.getUserPassword()).getBytes(StandardCharsets.UTF_8)));
                    return userMapper.insert(entity);
                }else{
                    User user = userMapper.selectById(entity.getId());
                    if (user == null)
                        return 0;
                    return userMapper.updateById(entity);
                }
            }else{
                return 0;
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    /**
     * 根据Id删除用户（逻辑删除）
     * @param Id
     * @return
     */
    @Override
    public int DeleteUser(String Id) {
        if (StringUtils.isNotBlank(Id)){
            User user = userMapper.selectById(Id);
            if (user == null)
                return 0;
            return userMapper.deleteById(Id);
        }else{
            return 0;
        }

    }
}




