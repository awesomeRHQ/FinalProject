package com.rhq.parkinglotsystem.controller;

import com.rhq.parkinglotsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dataManagement")
public class dataManagementController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<?> findAllMessage(){

        return null;
    }

    @RequestMapping("/fingOne")
    public List<?> findMessageByParams(){

        return null;
    }

    @RequestMapping("/updateOne")
    public void updateMessage(){

    }
}
