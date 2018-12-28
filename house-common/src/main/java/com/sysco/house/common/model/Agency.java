package com.sysco.house.common.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("agency")
public class Agency implements Serializable {
    private Integer id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String aboutMe;

    private String mobile;

    private String webSite;
}