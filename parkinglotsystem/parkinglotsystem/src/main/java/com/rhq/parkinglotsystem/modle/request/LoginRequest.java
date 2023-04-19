package com.rhq.parkinglotsystem.modle.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {

    public String userAccount;

    public String password;
}
