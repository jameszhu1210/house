package com.sysco.house.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.sysco.house.biz.service.AgencyService;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.BasePageRequestBean;
import com.sysco.house.common.response.GenericResponse;
import com.sysco.house.common.response.ResAgencyDetail;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @RequestMapping(value = "agency/list", method = RequestMethod.POST)
    @ApiOperation(value = "agency/list", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse agencyList(@RequestBody BasePageRequestBean condition){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("查询经纪人列表页成功");
        Page<User> agencyList = agencyService.getAgencyList(condition);
        genericResponse.setResponse(agencyList);
        return genericResponse;
    }

    @RequestMapping(value = "agency/detail", method = RequestMethod.GET)
    @ApiOperation(value = "agency/detail", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse agencyDetail(Long id){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("查询经纪人详情成功");
        ResAgencyDetail resAgencyDetail = agencyService.agencyDetail(id);
        genericResponse.setResponse(resAgencyDetail);
        return genericResponse;
    }
}
