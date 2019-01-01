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
@TableName("house_msg")
public class HouseMsg extends Model<HouseMsg> implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long houseId;

    private Long agentId;

    private String userName;

    private Date createTime;

    private String msg;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
