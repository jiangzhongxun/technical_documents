此项目测试案例：
1. 异步任务功能案例
    - 主启动类上面添加 @EnableAsync // 开启支持异步任务
    - service 服务方法上添加  @Async  // 支持异步任务
2. 邮件任务功能案例
    - 添加 pom 依赖
    ```xml
               <!--
                   mail邮件发送
                   SpringBoot 2.4.1 版本中没有 mail 这个包的依赖信息
                   所以要使用的话，必须要明确的指定 mail 包的 version
               -->
               <dependency>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-mail</artifactId>
                   <version>2.1.3.RELEASE</version>
               </dependency> 
    ```
    - 首先 qq 邮箱要开启 stmp 支持
    - 配置邮件任务所需信息
    ```properties
       # main 邮箱发送配置
       spring.mail.username=bai211425401@qq.com
       spring.mail.password=xojjvkmrgzlccabc
       spring.mail.host=smtp.qq.com
       # 开启加密验证
       spring.mail.properties.mail.stmp.ssl.enable=true
       
       # 自定义邮件的发送人
       custom.mail.from=bai211425401@qq.com
       # 自定义邮件的接收人
       custom.mail.to=bai211425401@qq.com
       # 复杂邮件发送携带附件路径配置
       custom.mail.attachment=F:/照片/2020-12/1.jpg
   ```
   - 发送一封简单的邮件
   ```java
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
   }
   ```
3. 定时任务功能案例
    - 主启动类添加 @EnableScheduling // 开启支持定时任务
    - 业务类方法上添加 @Scheduled(cron = "* * * * * ?") 开启定时任务执行
4. SpringBoot 整合 redis
    - 添加 pom 依赖
    ```xml
           <!--springboot整合redis-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-redis</artifactId>
                <version>2.3.1.RELEASE</version>
            </dependency>
    ```
   - 再 application.properties 中配置 redis 相关信息
   ```properties
   # redis相关配置
   spring.redis.host=localhost
   spring.redis.port=6379
   ```
   - 测试即可
   ```java
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
       @Autowired
       RedisTemplate<String, Object> redisTemplate;
   
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

   ```
5. 自定义 RedisTemplate 实现 jackson 序列化
    - 自定义 RedisConfig 配置类源码
    ```java
    package com.bai.config;
    
    import com.fasterxml.jackson.annotation.JsonAutoDetect;
    import com.fasterxml.jackson.annotation.PropertyAccessor;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.redis.connection.RedisConnectionFactory;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
    import org.springframework.data.redis.serializer.StringRedisSerializer;
    
    /**
     * @author: 南独酌酒 <211425401@126.com>
     * @date: 2020/12/19 16:38
     */
    @Configuration
    public class RedisConfig {
        /**
         * 自定义 RedisTemplate
         */
        @Bean
        @SuppressWarnings("all")
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
            // 为了开发方便,一般直接使用 <String, Object> 类型
            RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
            template.setConnectionFactory(factory);
    
            // Json 序列化配置
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    
            // String 的序列化
            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    
            // key采用String的序列化方式
            template.setKeySerializer(stringRedisSerializer);
            // hash的key也采用String的序列化方式
            template.setHashKeySerializer(stringRedisSerializer);
            // value序列化方式采用 jackson
            template.setValueSerializer(jackson2JsonRedisSerializer);
            // hash的value序列化方法也采用 jsckson
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            // 载入自定义 RedisTemplate 的配置信息
            template.afterPropertiesSet();
            return template;
        }
    }

    ```