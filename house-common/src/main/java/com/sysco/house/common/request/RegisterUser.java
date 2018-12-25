package com.sysco.house.common.request;

import lombok.Data;

@Data
public class RegisterUser {
    private String name;

    private String phone;

    private String email;

    private String aboutMe;

    private String passwd;

    private Integer type;

}
