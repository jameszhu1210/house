package com.sysco.house.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class HouseListCondition extends BasePageRequestBean{
    //小区
    private String  communityName;

    //类型
    private Integer type;

    //用户id
    @ApiModelProperty(hidden = true)
    private Integer userId;

    //房屋id
    @ApiModelProperty(hidden = true)
    private Long houseId;

    //房屋列表
    @ApiModelProperty(hidden = true)
    private List<Long> idList;
}
