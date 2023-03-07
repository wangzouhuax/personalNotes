package com.wzh.mqtt.controller;

import com.wzh.mqtt.result.Result;
import com.wzh.mqtt.result.ResultCodeEnum;
import com.wzh.mqtt.service.MqttGatewayIotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangzouhuax
 */
@Controller
@RequestMapping(value = "/mqtt-iot")
@Api(value = "MqttController",tags = {"MQTT -发送Iot主题的消息"})
public class MqttIotController {

    @Autowired
    private MqttGatewayIotService gatewayService;

    @ApiOperation("向指定主题中，发送消息")
    @PostMapping(value = "/sendIot")
    @ResponseBody
    public Result<String> sendMqtt(@RequestParam(value = "topic")@ApiParam(value = "MQTT 发送Iot主题的消息") String topic,
                                   @RequestParam(value = "data")@ApiParam(value = "消息") String data){

        try{
            // topic 可以带有通配符/iot/#/test
            gatewayService.sendMessageToMqtt(data,topic);
            return Result.success(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(ResultCodeEnum.FAIL);

    }


}
