package com.wzh.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author wangzouhuax
 * @Description 与第三方平台进行数据交互
 * @create 2023-03-02 17:38
 */
@Service
public class ThirdPartyConsumer {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyConsumer.class);


    @KafkaListener(topics = {"first","second"},groupId = "my-group")
    public void consumeThirdPartyData(String message) {
        log.info("正在订阅主题 --> first");
        // 处理第三方数据
        log.info("获取消息：{}",message);

    }
}
