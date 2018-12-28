package com.sysco.house.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class AddHouse {

    private String name;

    private Integer type;

    private Integer price;

    private Integer area;

    private Integer beds;

    private Integer baths;

    private Double rating;

    private String remakes;

    private String properties;

    private String tags;

    private Integer cityId;

    private Integer communityId;

    private String address;
    @ApiModelProperty(hidden = true)
    private List<MultipartFile> imageFiles = new ArrayList<>();
    @ApiModelProperty(hidden = true)
    private List<MultipartFile> floorPlanFiles = new ArrayList<>();
}
