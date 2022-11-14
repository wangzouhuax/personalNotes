package com.wzh.mqtt.common;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 配置基本信息
     * @return
     */
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("wzh")
                .select()
                //只显示api路径下的页面
                .apis(RequestHandlerSelectors.basePackage("com.wzh.mqtt.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MQTT 发送测试 ")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("wzh", "http://wzh.com", "wangzouhuax@163.com"))
                .build();
    }


}
