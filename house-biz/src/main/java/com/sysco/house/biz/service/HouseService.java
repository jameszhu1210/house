package com.sysco.house.biz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.common.model.House;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.AddHouse;
import com.sysco.house.common.request.OwnListCondition;

public interface HouseService {
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
     * 个人房产信息页查询
     * @param condition
     */
    Page<House> houseOwnList(OwnListCondition condition);
}
