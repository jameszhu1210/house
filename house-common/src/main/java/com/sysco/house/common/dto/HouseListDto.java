package com.sysco.house.common.dto;

import com.sysco.house.common.model.House;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HouseListDto extends House {

    private String communityName;

    private String cityName;

    private String firstImage;

    private List<String> imageFiles = new ArrayList<>();

    private List<String> floorPlanFiles = new ArrayList<>();

}
