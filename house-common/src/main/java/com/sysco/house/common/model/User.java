package com.sysco.house.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
