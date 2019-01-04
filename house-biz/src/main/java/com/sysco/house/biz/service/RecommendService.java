package com.sysco.house.biz.service;

import com.sysco.house.common.dto.HouseListDto;
import java.util.List;

public interface RecommendService {

    /**
     * 热门房产计数器，点击详情计数加1
     * @param id
     */
    double increase(Long id);

    /**
     * 获取热门房产id
     * @return
     */
    List<Long> getHot();

    /**
     * 获取热门房产列表
     */
    List<HouseListDto> getHotHouse();


}
