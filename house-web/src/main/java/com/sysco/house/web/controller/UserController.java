package com.sysco.house.web.controller;

import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.common.model.User;
import com.sysco.house.common.model.request.RegisterUser;
import com.sysco.house.common.model.response.GenericResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册功能
     * 1.注册验证 2.发送邮件 3.失败跳转到注册页面
     * @param user
     * @return
     */
    @RequestMapping(value = "accounts/register", method = RequestMethod.POST)
    @ApiOperation(value = "accounts/register", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse accountsRegister(RegisterUser user, @RequestParam("file") MultipartFile file){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        User zhy = new User().setName("zhy").setEmail("123@qq.com");
        Integer insert = userMapper.insert(zhy);
        return genericResponse;
    }

}
