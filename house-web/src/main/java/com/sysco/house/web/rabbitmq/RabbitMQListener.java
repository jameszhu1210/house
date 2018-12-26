package com.sysco.house.web.rabbitmq;


import com.sysco.house.biz.mapper.UserMapper;
import com.sysco.house.biz.service.MailService;
import com.sysco.house.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class RabbitMQListener {

    private final static Logger log= LoggerFactory.getLogger("listen");

    @Value("${domain.name}")
    private String domainName;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @RabbitListener(queues = "vendor.send.email.queue",containerFactory = "singleListenerContainer")
    public void sendEmailMessage(@Payload User record){
        try {
            log.info("消费者监听交易记录信息： {} ",record.getName());
            String link = "http://" + domainName + "/accounts/verity?key=" + record.getEmail();
            mailService.sendMail("用户激活", link, record.getEmail());
        }catch (Exception e){
            log.error("消息体解析 发生异常； ",e.fillInStackTrace());
        }
    }

    @RabbitListener(queues = "vendor.delay.queue",containerFactory = "singleListenerContainer")
    public void delayMessage(@Payload User record){
        try {
            User u = new User().setEmail(record.getEmail());
            User user = userMapper.selectOne(u);
            if(user.getEnable() == 0){
                log.info("未激活： {} ","删除信息。。。。");
                userMapper.deleteById(user.getId());
            }
        }catch (Exception e){
            log.error("消息体解析 发生异常； ",e.fillInStackTrace());
        }
    }

}
