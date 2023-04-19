package com.rhq.parkinglotsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhq.parkinglotsystem.mapper.CarMapper;
import com.rhq.parkinglotsystem.mapper.CustomerMapper;
import com.rhq.parkinglotsystem.mapper.ParkingplaceMapper;
import com.rhq.parkinglotsystem.modle.Customer;
import com.rhq.parkinglotsystem.modle.Parkingplace;
import com.rhq.parkinglotsystem.modle.response.LoginCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ParkinglotsystemApplicationTests {

    @Autowired
    private ParkingplaceMapper parkingplaceMapper;

    private CarMapper carMapper;
    @Autowired
    public CustomerMapper customerMapper;

    public static double REGULAR_TIME = 0.5;
    public static int A_COST = 5;
    @Test
    void ComputeCost() {
        long a = 1681230731000L;
        long b = 1681223585000L;
        float parkTime = Float.parseFloat(String.format("%.2f",(float)( a- b) /1000/3600));
        System.out.println("parktime:"+parkTime);
        if (parkTime < REGULAR_TIME) {
            System.out.println("pay:" + 0);
        } else {
            Float x = Float.parseFloat (String.format("%.2f",(float)(parkTime - REGULAR_TIME) / REGULAR_TIME));
            System.out.println("x:" + x);
            if (x < 1) {
                System.out.println("pay:" + A_COST);
            } else {
                System.out.println("pay:" + (x.intValue() * A_COST+A_COST));
            }
        }
    }
    @Test
    void insertParkingplace() {
//        QueryWrapper<Parkingplace> qw = new QueryWrapper<>();
//        List<Parkingplace> parkingplaces = parkingplaceMapper.selectList(qw);
        DecimalFormat df = new DecimalFormat("0.000000");//设置保留位数
        for (int i = 1; i <= 21; i++) {
            Parkingplace parkingplace = new Parkingplace();
            double longitude = Double.valueOf(df.format((float) (730) / 29 * i / 1000000.0));
            double latitude = Double.valueOf(df.format((float) (340) / 29 * i / 1000000.0));
//            double longitude = Double.valueOf(df.format((float) (333) / 25 * i / 1000000.0));
//            double latitude = Double.valueOf(df.format((float) (579) / 25 * i / 1000000.0));
            parkingplace.setLongitude(Double.valueOf(df.format(longitude + 113.551157)));
            parkingplace.setLatitude(Double.valueOf(df.format(22.361536 - latitude )));
            parkingplace.setParkingPlaceId("G"+(i+8));
            int insert = parkingplaceMapper.insert(parkingplace);
            System.out.println(insert);

//            System.out.println(Double.valueOf(df.format(longitude + 113.550605)) + "," + df.format(latitude + 22.360977));
        }
    }

    @Test
    void Selectone(){
        LoginCustomer asd = customerMapper.getLoginCustomer("oLKZL5UsxJQkdvzfMtRkMKRGjSQ4");
        System.out.println(asd);
    }

}
