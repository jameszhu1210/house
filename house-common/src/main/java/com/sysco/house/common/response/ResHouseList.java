package com.sysco.house.common.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ResHouseList{
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

    private String communityName;

    private String cityName;

    private String firstImage;

    private List<String> imageFiles = new ArrayList<>();

    private List<String> floorPlanFiles = new ArrayList<>();
}
