package com.rhq.parkinglotsystem.modle.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class LoginCustomer {

    public Integer id;

    public String openId;

    @JsonProperty("carId")
    public String carId;

    public String parkingPlaceId;

    public int isBooking;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date deadline;

    public Double latitude;

    public Double longitude;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date enterTime;

    public Date leaveTime;

    public float parkTime;

    public int cost;

    public String vip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date vipDeadline;
}
