package com.wzh.mqtt.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * @author wangzouhuax
 */
@Service
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannelHome")// 往哪个信道channel发消息
public interface MqttGatewayHomeService {

    /*
     * 发送 MQTT 的消息有三种：
     *  直接发送消息
     *  指定主题发送
     *  指定主题和qos
     */

    void sendMessageToMqtt(String data);

    // 主题名称不得包含任何通配符 （The topic name must not contain any wildcard characters(#+)）
    void sendMessageToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

    void sendMessageToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos);
}
