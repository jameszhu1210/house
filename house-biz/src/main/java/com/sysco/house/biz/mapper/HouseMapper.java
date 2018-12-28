package com.sysco.house.biz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.common.model.House;
import com.sysco.house.common.request.OwnListCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HouseMapper extends BaseMapper<House> {
    List<House> selectOwnList(Page page,OwnListCondition condition);
}
