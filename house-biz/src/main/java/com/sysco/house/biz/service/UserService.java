package com.sysco.house.biz.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.RegisterUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    /**
     * 1. 插入数据库，非激活； 密码md5加密；保存头像到本地
     * 2. 生成key 绑定emai
     * 3. 发送邮件给用户
     * @param account
     * @param file
     */
    void addAccount(RegisterUser account, MultipartFile file);

    /**
     * 激活用户，enable设为1
     * @param key
     */
    void verityAccount(String key);

    /**
     * 登陆用户
     * @param username
     * @param password
     */
    User auto(String username, String password);

    /**
     * 修改用户接口
     * @param account
     */
    void updateUser(RegisterUser account);

    /**
     * 根据条件查询user
     * @param ew
     */
    List<User> getUseByQuery(EntityWrapper<User> ew);
}
