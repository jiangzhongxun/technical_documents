package com.bai.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidStatService;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid 数据源相关配置
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/10 21:56
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<>(2);
        //  增加配置
        initParameters.put("loginUsername", "never");
        initParameters.put("loginPassword", "never123");
        // 谁可以访问
        //initParameters.put("allow", "localhost");
        bean.setInitParameters(initParameters);
        return bean;
    }

}
