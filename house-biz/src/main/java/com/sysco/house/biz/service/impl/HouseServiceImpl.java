package com.sysco.house.biz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.sysco.house.biz.mapper.HouseMapper;
import com.sysco.house.biz.mapper.HouseUserMapper;
import com.sysco.house.biz.service.FileService;
import com.sysco.house.biz.service.HouseService;
import com.sysco.house.common.model.House;
import com.sysco.house.common.model.HouseUser;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.AddHouse;
import com.sysco.house.common.request.OwnListCondition;
import com.sysco.house.common.utils.FormatterUtils;
import com.sysco.house.common.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private FileService fileService;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Override
    public void addHouse(AddHouse house, User user) {
        Date createTime = FormatterUtils.getCreateTime();
        House hs = new House();
        //上架
        hs.setState(1);
        hs.setCreateTime(createTime);
        ObjectUtil.transfer(house, hs);
        if(CollectionUtils.isNotEmpty(house.getImageFiles())){
            List<String> imgPath = fileService.getImgPath(house.getImageFiles());
            String images = imgPath.stream().collect(Collectors.joining(","));
            hs.setImages(images);
        }
        if(CollectionUtils.isNotEmpty(house.getFloorPlanFiles())){
            List<String> floorPath = fileService.getImgPath(house.getFloorPlanFiles());
            String floors = floorPath.stream().collect(Collectors.joining(","));
            hs.setFloorPlan(floors);
        }
        houseMapper.insert(hs);
        bindHouse2User(hs.getId(), user.getId(),1);
    }


    @Override
    public void bindHouse2User(Long houseId, Long userId, Integer type) {
        HouseUser houseUser = new HouseUser()
                .setHouseId(houseId).setUserId(userId).setType(type)
                .setCreateTime(FormatterUtils.getCreateTime());
        houseUserMapper.insert(houseUser);
    }

    @Override
    public Page<House> houseOwnList(OwnListCondition condition) {
        Page<House> page = new Page<>(condition.getOffset(),condition.getLimit());
/*        EntityWrapper<HouseUser> ew = new EntityWrapper<>();
        ew.eq("name", condition.getName());
        ew.eq("type", condition.getType());
        ew.orderBy(condition.getOrder(), condition.getDesc());*/
        page.setRecords(houseMapper.selectOwnList(page,condition));
        return page;
    }
}
