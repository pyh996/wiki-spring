package com.wiki.spring.config;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;



@ComponentScan("com.wiki")
@SpringBootApplication
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://192.168.31.6:{}", env.getProperty("server.port"));
    }
}
