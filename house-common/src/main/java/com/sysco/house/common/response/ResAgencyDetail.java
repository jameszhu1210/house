package com.sysco.house.common.response;

import com.sysco.house.common.model.House;
import com.sysco.house.common.model.User;
import lombok.Data;

import java.util.List;
@Data
public class ResAgencyDetail {
    private User info;

    private List<House> houses;
}
