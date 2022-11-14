package com.wzh.mqtt.controller;

import com.wzh.mqtt.result.Result;
import com.wzh.mqtt.result.ResultCodeEnum;
import com.wzh.mqtt.service.MqttGatewayHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangzouhuax
 */
@Controller
@RequestMapping(value = "/mqtt-home")
@Api(value = "MqttController",tags = {"MQTT 发送home主题的消息"})
public class MqttHomeController {


    @Autowired
    private MqttGatewayHomeService homeService;


    @ApiOperation("向指定主题中，发送消息")
    @RequestMapping(value = "/sendHome",method = RequestMethod.POST)
    @ResponseBody
    public Result sendMqtt(@RequestParam(value = "topic")@ApiParam(value = "MQTT 发送home主题") String topic,
                           @ApiParam(value = "消息") String data){

        try{
            // topic 可以带有通配符/home/#/test
            homeService.sendMessageToMqtt(data,topic);
            return Result.success(data);
        } catch (Exception e) {

        }
        return Result.error(ResultCodeEnum.FAIL);

    }


}
