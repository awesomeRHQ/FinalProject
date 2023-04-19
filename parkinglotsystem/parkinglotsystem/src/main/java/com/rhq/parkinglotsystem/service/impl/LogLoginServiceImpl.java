package com.rhq.parkinglotsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhq.parkinglotsystem.modle.LogLogin;
import com.rhq.parkinglotsystem.service.LogLoginService;
import com.rhq.parkinglotsystem.mapper.LogLoginMapper;
import org.springframework.stereotype.Service;

/**
* @author 20725
* @description 针对表【log_login】的数据库操作Service实现
* @createDate 2023-01-03 13:00:16
*/
@Service
public class LogLoginServiceImpl extends ServiceImpl<LogLoginMapper, LogLogin>
    implements LogLoginService{

}




