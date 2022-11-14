package com.wzh.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@IntegrationComponentScan
public class MqttOutboundConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MqttOutboundConfiguration.class);

    @Autowired
    private MqttConfiguration mqttProperties;


    /**
     * 创建 IOT 的信道
     * @return
     */
    @Bean
    public MessageChannel mqttOutboundChannelIOT() {
        return new DirectChannel();
    }


    /**
     * 创建 home 的信道
     * @return
     */
    @Bean
    public MessageChannel mqttOutboundChannelHome() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        String[] mqttServerUrls = mqttProperties.getUrl().split(",");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttServerUrls);
        options.setUserName(options.getUserName());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        // 接收离线消息   告诉代理客户端是否需要建立持久会话
        // false为建立持久会话
        options.setCleanSession(false);
        options.setKeepAliveInterval(2);
        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * 发送消息   通过tcp信道  mqttOutboundChannelIOT
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannelIOT")
    public MessageHandler mqttOutboundIot() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(mqttProperties.getClientId() + "_outbound_iot",mqttClientFactory());
        messageHandler.setAsync(true);
        // 没有通配符
        messageHandler.setDefaultTopic("iot");
        return messageHandler;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannelHome")
    public MessageHandler mqttOutboundHome() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(mqttProperties.getClientId() + "_outbound_home",mqttClientFactory());
        messageHandler.setAsync(true);
        // 携带通配符
        messageHandler.setTopicExpressionString("headers['home/#']");
        return messageHandler;
    }






}
