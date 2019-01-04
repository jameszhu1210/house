package com.sysco.house.biz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.biz.service.HouseService;
import com.sysco.house.biz.service.RecommendService;
import com.sysco.house.common.dto.HouseListDto;
import com.sysco.house.common.request.HouseListCondition;
import com.sysco.house.common.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendServiceImpl implements RecommendService {
    private final static String HOT_HOUSE = "hot_house";

    private final static int SIZE = 5;

    @Autowired
    private HouseService houseService;

    @Override
    public double increase(Long id) {
        JedisUtil instance = JedisUtil.getInstance();
        JedisUtil.SortSet sortset = instance.SORTSET;
        double zincrby = sortset.zincrby(HOT_HOUSE, 1.0D, String.valueOf(id));
        return zincrby;

    }

    @Override
    public List<Long> getHot() {
        JedisUtil instance = JedisUtil.getInstance();
        JedisUtil.SortSet sortset = instance.SORTSET;
        Set<String> zrevrange = sortset.zrevrange(HOT_HOUSE, 0, -1);
        List<Long> collect = zrevrange.stream().map(Long::parseLong).collect(Collectors.toList());
        if(!collect.isEmpty()){
            return collect;
        }
        return Collections.emptyList();
    }

    @Override
    public List<HouseListDto> getHotHouse() {
        List<Long> list = getHot();
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        list = list.subList(0, Math.min(list.size(), SIZE));

        HouseListCondition condition = new HouseListCondition();
        condition.setLimit(SIZE);
        condition.setOffset(0);
        condition.setIdList(list);
        Page<HouseListDto> houseListDtoPage = houseService.queryHouseList(condition);
        List<HouseListDto> records = houseListDtoPage.getRecords();

        List<HouseListDto> orderList = new ArrayList<>();
        list.forEach(s -> {
            Optional<HouseListDto> first = records.stream().filter(r -> r.getId().equals(s)).findFirst();
            first.ifPresent(f -> {
                orderList.add(f);
            });
        });
        return orderList;
    }
}
