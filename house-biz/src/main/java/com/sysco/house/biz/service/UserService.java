package com.sysco.house.biz.service;

import com.sysco.house.common.request.RegisterUser;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    /**
     * 1. 插入数据库，非激活； 密码md5加密；保存头像到本地
     * 2. 生成key 绑定emai
     * 3. 发送邮件给用户
     * @param account
     * @param file
     */
    void addAccount(RegisterUser account, MultipartFile file);
}
