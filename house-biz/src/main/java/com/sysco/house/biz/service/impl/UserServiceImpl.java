package com.sysco.house.biz.service.impl;

import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.biz.service.FileService;
import com.sysco.house.biz.service.UserService;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.RegisterUser;
import com.sysco.house.common.utils.FormatterUtils;
import com.sysco.house.common.utils.HashUtils;
import com.sysco.house.common.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FileService fileService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addAccount(RegisterUser account, MultipartFile file) {
        User user = new User();
        String md5pw = HashUtils.encryPassword(account.getPasswd());
        account.setPasswd(md5pw);
        List<String> imgPath = fileService.getImgPath(Arrays.asList(file));
        if(!imgPath.isEmpty()){
            user.setAvatar(imgPath.get(0));
        }
        user.setEnable(0);
        user.setCreateTime(FormatterUtils.getCreateTime());
        ObjectUtil.transfer(account, user);
        userMapper.insert(user);
    }
}
