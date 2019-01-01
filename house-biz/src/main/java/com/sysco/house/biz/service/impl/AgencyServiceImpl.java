package com.sysco.house.biz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.biz.mapper.AgencyMapper;
import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.biz.service.AgencyService;
import com.sysco.house.biz.service.HouseService;
import com.sysco.house.common.dto.HouseListDto;
import com.sysco.house.common.model.Agency;
import com.sysco.house.common.model.House;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.BasePageRequestBean;
import com.sysco.house.common.request.HouseListCondition;
import com.sysco.house.common.request.OwnListCondition;
import com.sysco.house.common.response.ResAgencyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AgencyMapper agencyMapper;

    @Autowired
    private HouseService houseService;

    @Override
    public Page<User> getAgencyList(BasePageRequestBean condition) {
        Page<User> page = new Page<>(condition.getOffset(),condition.getLimit());
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("enable", 1);
        ew.eq("type",2);
        List<User> agencies = userMapper.selectPage(page, ew);
        if(!CollectionUtils.isEmpty(agencies)){
            page.setRecords(agencies);
        }
        return page;

    }

    @Override
    public ResAgencyDetail agencyDetail(Long id) {
        ResAgencyDetail resAgencyDetail = new ResAgencyDetail();
        User agency = userMapper.selectById(id);
        OwnListCondition condition = new OwnListCondition();
        condition.setOffset(0);
        condition.setLimit(10);
        condition.setBookmake(1);
        condition.setUserId(id);
        Page<House> housePage = houseService.houseOwnList(condition);
        if(!CollectionUtils.isEmpty(housePage.getRecords())){
            resAgencyDetail.setHouses(housePage.getRecords());
        }
        resAgencyDetail.setInfo(agency);
        return resAgencyDetail;
    }
}
