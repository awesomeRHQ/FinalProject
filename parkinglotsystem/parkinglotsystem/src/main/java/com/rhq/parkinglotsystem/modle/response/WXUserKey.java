package com.rhq.parkinglotsystem.modle.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class WXUserKey {
    public String openid;

    public String session_key;

    public String unionid;

}
