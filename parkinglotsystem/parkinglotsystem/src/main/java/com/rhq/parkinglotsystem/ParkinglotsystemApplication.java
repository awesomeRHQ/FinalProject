package com.rhq.parkinglotsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.rhq.parkinglotsystem.mapper")
@MapperScan("com.*.Service")
@EnableTransactionManagement //开启事务
@EnableScheduling //开启定时服务
@SpringBootApplication
public class ParkinglotsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkinglotsystemApplication.class, args);
    }

}
