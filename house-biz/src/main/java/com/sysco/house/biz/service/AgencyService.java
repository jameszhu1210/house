package com.sysco.house.biz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.BasePageRequestBean;
import com.sysco.house.common.response.ResAgencyDetail;

public interface AgencyService {

    Page<User> getAgencyList(BasePageRequestBean condition);

    /**
     * 1. 经纪人详情， 2. 所代理的房产列表
     * @param id
     */
    ResAgencyDetail agencyDetail(Long id);
}
