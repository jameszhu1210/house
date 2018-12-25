package com.sysco.house.biz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sysco.house.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper  extends BaseMapper<User> {

}
