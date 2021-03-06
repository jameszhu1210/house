package com.sysco.house.biz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sysco.house.common.model.Agency;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AgencyMapper extends BaseMapper<Agency> {

}
