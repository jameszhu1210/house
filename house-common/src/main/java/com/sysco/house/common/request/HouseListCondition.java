package com.sysco.house.common.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HouseListCondition extends BasePageRequestBean{
    //小区
    private String  communityName;

    //类型
    private Integer type;


}
