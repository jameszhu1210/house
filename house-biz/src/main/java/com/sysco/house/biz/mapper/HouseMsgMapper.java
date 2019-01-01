package com.sysco.house.biz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sysco.house.common.model.HouseMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HouseMsgMapper extends BaseMapper<HouseMsg> {

}
