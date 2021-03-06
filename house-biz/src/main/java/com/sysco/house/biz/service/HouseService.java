package com.sysco.house.biz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.common.dto.HouseListDto;
import com.sysco.house.common.model.House;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.AddHouse;
import com.sysco.house.common.request.AddHouseMsg;
import com.sysco.house.common.request.HouseListCondition;
import com.sysco.house.common.request.OwnListCondition;
import com.sysco.house.common.response.ResHouseDetail;

public interface HouseService {
    /**
     * 查询房屋列表接口
     * @param condition
     */
    Page<HouseListDto> queryHouseList(HouseListCondition condition);
    /**
     * 添加房屋接口
     * 1 添加房屋图片
     * 2 添加户型图图片
     * 3 插入房产信息
     * 4 绑定用户和房屋关系
     * @param house
     * @param user
     */
    void addHouse(AddHouse house, User user);

    /**
     * 绑定用户和房屋 接口
     * @param houseId
     * @param userId
     * @param type
     */
    void bindHouse2User(Long houseId, Long userId, Integer type);

    /**
     * 取消绑定用户和房屋 接口
     * @param houseId
     * @param userId
     * @param type
     */
    void unbindHouse2User(Long houseId, Long userId, Integer type);

    /**
     * 个人房产信息页查询
     * @param condition
     */
    Page<House> houseOwnList(OwnListCondition condition);

    /**
     * 房屋详细信息接口
     * @param id
     * @return
     */
    ResHouseDetail houseDetail(Long id);

    /**
     * 用户留言功能
     * @param houseMsg
     */
    void houseLeaveMsg(AddHouseMsg houseMsg);

    /**
     * 用户评分功能
     * @param id
     * @param rating
     */
    void houseRating(Long id, Double rating);
}
