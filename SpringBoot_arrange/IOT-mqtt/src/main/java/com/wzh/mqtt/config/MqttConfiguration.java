package com.wzh.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.mqtt")
@Data
public class MqttConfiguration {

    private String url;
    private String clientId;
    private String topic;
    private String username;
    private String password;
    private String timeout;
    private String keepalive;
}
