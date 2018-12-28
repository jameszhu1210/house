package com.sysco.house.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.biz.service.HouseService;
import com.sysco.house.biz.service.UserService;
import com.sysco.house.common.dto.HouseListDto;
import com.sysco.house.common.model.House;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.AddHouse;
import com.sysco.house.common.request.HouseListCondition;
import com.sysco.house.common.request.OwnListCondition;
import com.sysco.house.common.response.GenericResponse;
import com.sysco.house.web.interceptor.UserContext;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class HouseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    /**
     * 添加房屋接口
     * 1 获取用户
     * 2 设置房产状态
     * 3.添加房产
     * @param house
     * @return
     */
    @RequestMapping(value = "house/add", method = RequestMethod.POST)
    @ApiOperation(value = "house/add", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse houseAdd(AddHouse house,  MultipartFile image1, MultipartFile image2,
                                     MultipartFile image3,  MultipartFile floor1,  MultipartFile floor2){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("添加房屋成功");

        User user = UserContext.getUser();
        if(null != image1){
            house.getImageFiles().add(image1);
        }
        if(null != image2){
            house.getImageFiles().add(image2);
        }
        if(null != image3){
            house.getImageFiles().add(image3);
        }
        if(null != floor1){
            house.getFloorPlanFiles().add(floor1);
        }
        if(null != floor2){
            house.getFloorPlanFiles().add(floor2);
        }
        houseService.addHouse(house, user);
        return genericResponse;
    }

    /**
     * 房屋列表接口
     * 1.实现分页
     * 2.支持小区搜索，类型搜索
     * 3.支持排序
     * 4.支持展示图片，价格，标题，地址等信息
     * @param condition
     * @return
     */
    @RequestMapping(value = "house/list", method = RequestMethod.POST)
    @ApiOperation(value = "house/list", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse houseList(@RequestBody HouseListCondition condition){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("查询房产列表页成功");
        Page<HouseListDto> houseListDtoPage = houseService.queryHouseList(condition);
        genericResponse.setResponse(houseListDtoPage);
        return genericResponse;
    }


    /**
     * 个人房产信息页
     * 1. 分页查询
     * 2  根据条件搜索 排序
     * @return
     */
    @RequestMapping(value = "house/ownlist", method = RequestMethod.POST)
    @ApiOperation(value = "house/ownlist", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse houseOwnList(@RequestBody OwnListCondition condition){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("查询个人房产信息页成功");
        User user = UserContext.getUser();
        condition.setUserId(user.getId());
        Page<House> housePage = houseService.houseOwnList(condition);
        genericResponse.setResponse(housePage);
        return genericResponse;
    }


}
