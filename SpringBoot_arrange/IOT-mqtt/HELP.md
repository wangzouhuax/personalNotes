# SpringBoot集成MQTT

Spring Integration 提供入站和出站通道适配器以支持消息队列遥测传输 (MQTT) 协议。

引入依赖
```xml
<dependency>
    <groupId>org.springframework.integration</groupId>
    <artifactId>spring-integration-mqtt</artifactId>
    <version>5.5.15</version>
</dependency>
```

XML 配置和本章的大部分内容是关于 MQTT v3.1 协议支持和各自的 Paho 客户端。有关相应协议的支持，请参阅 MQTT v5 支持段落


    使用 DefaultMqttPahoClientFactory 实现两个适配器的配置。有关配置选项的更多信息，请参阅 Paho 文档。

    我们建议配置一个 MqttConnectOptions 对象并将其注入工厂，而不是在工厂本身上设置（不推荐使用的）选项

## Inbound (Message-driven) Channel Adapter 
    入站（消息驱动）通道适配器
入站通道适配器由 MqttPahoMessageDrivenChannelAdapter 实现。为方便起见，您可以使用命名空间进行配置。一个最小的配置可能如下：








### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#using.devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)

