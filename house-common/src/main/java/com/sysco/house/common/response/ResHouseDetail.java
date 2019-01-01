package com.sysco.house.common.response;

import com.sysco.house.common.dto.HouseListDto;
import com.sysco.house.common.model.User;
import lombok.Data;

@Data
public class ResHouseDetail {
    private HouseListDto house;

    private User agency;
}
