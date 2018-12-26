package com.sysco.house.web.controller;


import com.sysco.house.biz.service.UserService;
import com.sysco.house.common.exception.ValidationException;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.RegisterUser;
import com.sysco.house.common.response.GenericResponse;
import com.sysco.house.web.utils.ValidateUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 激活用户接口
     * @param key
     * @return
     */
    @RequestMapping(value = "accounts/verity", method = RequestMethod.GET)
    @ApiOperation(value = "accounts/verity", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse accountsVerity(String key){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("激活成功");
        userService.verityAccount(key);
        return genericResponse;
    }

    /**
     *  登陆接口
     * @param request
     * @return
     */
    @RequestMapping(value = "accounts/sign-in", method = RequestMethod.POST)
    @ApiOperation(value = "accounts/sign-in", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse signIn(HttpServletRequest request, @RequestParam String username,
                                  @RequestParam String password){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("登陆成功");
        if(username == null || password == null){
            throw new ValidationException(HttpStatus.OK, "跳转到登陆页面");
        }else{
            User user = userService.auto(username, password);
            if(user == null){
                genericResponse.setMsg("");
                genericResponse.setResult(false);
                genericResponse.setErrorMessage("用户名密码错误");
            }else{
                HttpSession session = request.getSession();
                User user1 = (User)session.getAttribute("user");
                session.setAttribute("loginUser", user);
                session.setAttribute("user", user);
                Map<String, User> map = new HashMap<>(1);
                map.put("user", user);
                genericResponse.setMapData(map);
            }
        }
        return genericResponse;
    }

    /**
     * 登出操作
     * @param request
     * @return
     */
    @RequestMapping(value = "accounts/logout", method = RequestMethod.POST)
    @ApiOperation(value = "accounts/logout", response = GenericResponse.class)
    @ResponseBody
    public GenericResponse logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        session.invalidate();
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResult(true);
        genericResponse.setMsg("登出成功");
        return genericResponse;
    }
}
