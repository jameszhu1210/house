package com.sysco.house.common.request;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddHouseMsg {

    private Long houseId;

    private Long agentId;

    private String userName;

    private String msg;

    //用户邮箱
    private String email;

    //经纪人邮箱
    private String agencyEmail;
}
