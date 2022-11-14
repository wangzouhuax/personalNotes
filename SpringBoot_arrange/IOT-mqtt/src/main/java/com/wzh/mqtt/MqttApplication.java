package com.wzh.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author wangzouhuax
 */
@SpringBootApplication
@ComponentScan("com.wzh")
public class MqttApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MqttApplication.class, args);

        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path");
        System.out.println("Swagger文档: http://localhost" + ":" + port + "/" + path + "/swagger-ui.html");


    }

}
