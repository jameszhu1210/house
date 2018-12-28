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
@TableName("house")
public class House extends Model<House> implements Serializable{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer type;

    private Integer price;

    private String images;

    private Integer area;

    private Integer beds;

    private Integer baths;

    private Double rating;

    private String remakes;

    private String properties;

    private String floorPlan;

    private String tags;

    private Date createTime;

    private Integer cityId;

    private Integer communityId;

    private String address;

    private Integer state;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
