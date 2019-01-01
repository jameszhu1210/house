package com.sysco.house.biz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.sysco.house.biz.mapper.HouseMapper;
import com.sysco.house.biz.mapper.HouseMsgMapper;
import com.sysco.house.biz.mapper.HouseUserMapper;
import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.biz.service.FileService;
import com.sysco.house.biz.service.HouseService;
import com.sysco.house.common.dto.HouseListDto;
import com.sysco.house.common.model.House;
import com.sysco.house.common.model.HouseMsg;
import com.sysco.house.common.model.HouseUser;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.AddHouse;
import com.sysco.house.common.request.AddHouseMsg;
import com.sysco.house.common.request.HouseListCondition;
import com.sysco.house.common.request.OwnListCondition;
import com.sysco.house.common.response.ResHouseDetail;
import com.sysco.house.common.utils.FormatterUtils;
import com.sysco.house.common.utils.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HouseServiceImpl implements HouseService {
    @Value("${file.prefix}")
    private String filePreFix;

    @Autowired
    private FileService fileService;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HouseMsgMapper houseMsgMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        page.setRecords(houseMapper.selectOwnList(page,condition));
        return page;
    }

    @Override
    public Page<HouseListDto> queryHouseList(HouseListCondition condition) {
        Page<HouseListDto> page = new Page<>(condition.getOffset(),condition.getLimit());
        List<HouseListDto> houseListDtos = houseMapper.queryHouseList(page, condition);
        houseListDtos.forEach(m -> {
            if (StringUtils.isNotBlank(m.getImages())) {
                m.setFirstImage(filePreFix + m.getImages().split(",")[0]);
                for (String s : m.getImages().split(",")) {
                    m.getImageFiles().add(filePreFix + s);
                }
            }
            if (StringUtils.isNotBlank(m.getFloorPlan())) {
                for (String s : m.getFloorPlan().split(",")) {
                    m.getFloorPlanFiles().add(filePreFix + s);
                }
            }
        });
        page.setRecords(houseListDtos);
        return page;
    }

    @Override
    public ResHouseDetail houseDetail(Long id) {
        ResHouseDetail detail = new ResHouseDetail();
        HouseListCondition condition = new HouseListCondition();
        condition.setOffset(0);
        condition.setLimit(10);
        condition.setHouseId(id);
        Page<HouseListDto> houseList = queryHouseList(condition);
        if(houseList != null && !houseList.getRecords().isEmpty()){
            detail.setHouse(houseList.getRecords().get(0));
            //查询房屋所属经纪人 type 2
            EntityWrapper<HouseUser> ew = new EntityWrapper<>();
            ew.eq("house_id",id);
            ew.eq("type",1);
            List<HouseUser> houseUsers = houseUserMapper.selectList(ew);
            if(!houseUsers.isEmpty()){
                User user = userMapper.selectById(houseUsers.get(0).getUserId());
                detail.setAgency(user);
            }
        }
        return detail;
    }

    @Override
    public void houseLeaveMsg(AddHouseMsg houseMsg) {
        HouseMsg hs = new HouseMsg();
        ObjectUtil.transfer(houseMsg,hs);
        hs.setCreateTime(FormatterUtils.getCreateTime());
        houseMsgMapper.insert(hs);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("leave.msg.exchange");
        rabbitTemplate.setRoutingKey("leave.msg");
        rabbitTemplate.convertAndSend(houseMsg, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,User.class.getName());
            return message;
        });
    }
}
