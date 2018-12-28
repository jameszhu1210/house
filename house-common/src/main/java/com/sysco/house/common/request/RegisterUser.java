package com.sysco.house.common.request;

import lombok.Data;

@Data
public class RegisterUser {
    private String name;

    private String phone;

    private String email;

    private String aboutMe;

    private String passwd;

    /*1-普通用户 2-经纪人*/
    private Integer type;

    /**
     * 经纪人额外信息
     */
    private String mobile;

    private String webSite;

}
