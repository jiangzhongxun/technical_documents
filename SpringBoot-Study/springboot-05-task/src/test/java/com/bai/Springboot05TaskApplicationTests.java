package com.bai;

import com.bai.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot05TaskApplicationTests {
    /**
     * SpringBoot 2.4.1 版本中没有 mail 这个包的依赖信息
     * 所以要使用的话，必须要明确的指定 mail 包的 version
     */
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 邮件发送人
     */
    @Value(value = "${custom.mail.from}")
    private String customMailFrom;

    /**
     * 邮件接收人
     */
    @Value(value = "${custom.mail.to}")
    private String customMailTo;

    /**
     * 邮件携带附件路径
     */
    @Value(value = "${custom.mail.attachment}")
    private String customMailAttachment;

    /**
     * 发送一封简单的邮件
     */
    @Test
    void sendSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 邮件的主题
        simpleMailMessage.setSubject("2021年要一起跨年吗?");
        // 邮件的内容
        simpleMailMessage.setText("我准备去上海的东方明珠，外滩那去跨年！");
        // 邮件的发送人
        simpleMailMessage.setFrom(customMailFrom);
        // 邮件的接收人
        simpleMailMessage.setTo(customMailTo);
        // 调用 send 方法发送一封简单的邮件
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送一封复杂的邮件
     */
    @Test
    void sendMimeMailMessage() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 创建一个帮助发送复杂邮件的帮助类, multipart 表示是否支持多文件发送
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 邮件的主题
        helper.setSubject("2020年快要结束了吗?");
        // 邮件的内容: 参数1:可以编写 html 的文本内容, 参数2:是否支持 html 写法
        helper.setText("<p style='color:red;'>hello,2020年还剩11天就结束了,要开心呀！</p>", true);
        // 邮件可以携带的附件内容
        helper.addAttachment("跨年图片.jpg", new File(customMailAttachment));
        // 邮件的发送人
        helper.setFrom(customMailFrom);
        // 邮件的接收人
        helper.setTo(customMailTo);
        // 调用 send 方法发送一封复杂的邮件
        mailSender.send(mimeMessage);
    }

    /**
     * SpringBoot 整合 redis 简单使用
     */
    @Test
    void redisTest() {
        redisTemplate.opsForValue().set("mykey", "hh");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    /**
     * SpringBoot 整合 redis 存储对象使用
     */
    @Test
    void redisTest2() throws JsonProcessingException {
        User user = new User("张三", "123", 3);
        // 如果要添加到 redis 中的实体类对象没有实现 Serializable 接口，也就是说没有实例化是不能进行网络传输的
        // 正常的企业开发，是直接将实体类转化成 json 格式进行传输的
        //        String jsonUser = new ObjectMapper().writeValueAsString(user);
        // 如果要直接传输实体类的话，只需要实现 Serializable 接口即可
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
