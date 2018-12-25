package com.sysco.house.web.controller;

import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.biz.service.UserService;
import com.sysco.house.common.exception.ValidationException;
import com.sysco.house.common.request.RegisterUser;
import com.sysco.house.common.response.GenericResponse;
import com.sysco.house.common.utils.FormatterUtils;
import com.sysco.house.web.utils.ValidateUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册功能
     * 1.注册验证 2.发送邮件 3.失败跳转到注册页面
     * @param account
     * @return
     */
    @RequestMapping(value = "accounts/register", method = RequestMethod.POST)
    @ApiOperation(value = "accounts/register", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse accountsRegister(RegisterUser account, @RequestParam("file") MultipartFile file){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("注册成功，去邮箱激活后，才能登陆");
        if(account == null || StringUtils.isBlank(account.getName())){
            throw new ValidationException(HttpStatus.OK, "跳转到注册页面");
        }
        //用户验证
        try {
            ValidateUtils.validRegisterUser(account);
        } catch (Exception e) {
            genericResponse.setMsg("");
            genericResponse.setResult(false);
            genericResponse.setErrorMessage(e.getMessage());
        }
        //用户新增
        userService.addAccount(account, file);
        return genericResponse;
    }

}
