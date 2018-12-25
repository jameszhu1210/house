package com.sysco.house.common.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterUser {
    private String name;

    private String phone;

    private String email;

    private String aboutMe;

    private String passwd;

    private String avatar;

    private String type;

    private Date createTime;

    private String enable;

    private Integer agencyId;


}
