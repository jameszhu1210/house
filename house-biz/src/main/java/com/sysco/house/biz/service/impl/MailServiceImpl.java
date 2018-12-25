package com.sysco.house.biz.service.impl;

import com.sysco.house.biz.service.MailService;
import com.sysco.house.common.exception.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Value("${spring.mail.from}")
    private String mailFrom;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendMail(String title, String link, String email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(link, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.info("send email error: " + e.getMessage());
            throw new ValidationException(HttpStatus.INTERNAL_SERVER_ERROR, "send email error");
        }
    }
}
