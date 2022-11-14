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
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.*;

import java.util.UUID;

@Configuration
@IntegrationComponentScan
public class MqttInboundConfiguration {


    private static final Logger logger = LoggerFactory.getLogger(MqttInboundConfiguration.class);

    @Autowired
    private MqttConfiguration mqttProperties;

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        String[] mqttServerUrls = mqttProperties.getUrl().split(",");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttServerUrls);
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        options.setKeepAliveInterval(2);

        // 接收离线消息
        options.setCleanSession(false);
        factory.setConnectionOptions(options);
        return factory;
    }


    /**
     * 配置client，监听Topic
     * 如果我要配置多个client，就模仿此方法，多写几个client
     * @return MessageProducer
     */
    @Bean
    public MessageProducer inbound() {
        String[] inboundTopics = mqttProperties.getTopic().split(",");
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttProperties.getUrl(), 
                mqttProperties.getClientId(),
                inboundTopics);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 接收消息
     * 通过通道获取数据，即处理MQTT 发送过来的消息，可以通过MQTT 工具发送测试数据
     * @return MessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")  // 异步处理
    public MessageHandler handler() {
        return message -> {
            Object payload = message.getPayload();
            MessageHeaders messageHeaders = message.getHeaders();
            UUID packetId = messageHeaders.getId();
            Object qos = messageHeaders.get(MqttHeaders.RECEIVED_QOS);
            Object receivedTopic = messageHeaders.get(MqttHeaders.RECEIVED_TOPIC);
            String handMessage = "MQTT Client " + "packetId ===> " + packetId
                                 + "\nReceive payLoad ===> " + payload
                                 + "\nQOS ===> " + qos + "\t Topics:" + receivedTopic;
            logger.debug(handMessage);
            System.out.println(handMessage);
        };
    }
}
