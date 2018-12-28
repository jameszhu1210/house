package com.sysco.house.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@TableName("community")
public class Community extends Model<Community> implements Serializable {
    private Integer id;

    private String cityCode;

    private String cityName;

    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}