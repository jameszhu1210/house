package com.sysco.house.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OwnListCondition extends BasePageRequestBean{
    private String name;

    private Integer type;

    @ApiModelProperty(hidden = true)
    private Long userId;

}
