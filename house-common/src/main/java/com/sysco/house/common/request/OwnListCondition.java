package com.sysco.house.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OwnListCondition extends BasePageRequestBean{
    private String name;

    //1销售 2出租
    private Integer type;

    @ApiModelProperty(hidden = true)
    private Long userId;

    //经纪人 1售卖  普通用户2收藏
    private Integer bookmake;

}
