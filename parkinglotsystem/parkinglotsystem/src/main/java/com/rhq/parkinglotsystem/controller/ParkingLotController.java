package com.rhq.parkinglotsystem.controller;

import com.rhq.parkinglotsystem.modle.response.SaftyParkingLot;
import com.rhq.parkinglotsystem.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkinglot")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    public List<SaftyParkingLot> GetList(){
        return parkingLotService.GetList();
    }
}
