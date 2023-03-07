package com.wzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;


    private static final String TOPIC_FIRST = "first";
    private static final String TOPIC_SECOND = "second";



    @GetMapping("/api/v1/{num}")
    public void sendMessage2(@PathVariable("num") String num){
//        kafkaTemplate.send(TOPIC_NAME,1,"message","这是一个消息: num  = {" + num +"}").addCallback(
        kafkaTemplate.send(TOPIC_FIRST,"这是一个消息: num  = {" + num +"}").addCallback(
                success -> {
                    String topic = success.getRecordMetadata().topic();
                    long offset = success.getRecordMetadata().offset();
                    int partition = success.getRecordMetadata().partition();
                    log.info("发送成功：topic={},partition={},offset={}",topic,partition,offset);
                },
                failure ->{
                    log.info("发送失败："+ failure.getMessage());
                }
        );
    }


    @GetMapping("/api/v2")
    public void sendMessage(@RequestParam("message") String message){

    }






}
