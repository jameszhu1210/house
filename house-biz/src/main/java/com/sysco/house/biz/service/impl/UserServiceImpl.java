package com.sysco.house.biz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.biz.service.FileService;
import com.sysco.house.biz.service.MailService;
import com.sysco.house.biz.service.UserService;
import com.sysco.house.common.exception.ValidationException;
import com.sysco.house.common.model.User;
import com.sysco.house.common.request.RegisterUser;
import com.sysco.house.common.utils.FormatterUtils;
import com.sysco.house.common.utils.HashUtils;
import com.sysco.house.common.utils.ObjectUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FileService fileService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        registerNotify(user);

    }

    /**
     * 异步发送邮件 使用mq
     * @param user
     */
    public void registerNotify(User user){
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("vendor.exchange");
        rabbitTemplate.setRoutingKey("vendor.insert");
        rabbitTemplate.convertAndSend(user, message -> {
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,User.class.getName());
            return message;
        });
    }

    @Override
    public void verityAccount(String key) {
        User u = new User().setEmail(key);
        User user = userMapper.selectOne(u);
        if(null == user){
            throw new ValidationException(HttpStatus.OK, "激活链接已经失效，请重新注册");
        }else{
            if(user.getEnable() == 0){
                User x = new User().setEnable(1);
                EntityWrapper<User> ew = new EntityWrapper<>();
                ew.eq("email", key);
                userMapper.update(x, ew);
            }
        }
    }
}
