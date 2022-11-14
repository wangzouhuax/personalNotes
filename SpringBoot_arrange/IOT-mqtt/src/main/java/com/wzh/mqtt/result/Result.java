package com.wzh.mqtt.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author wangzh
 * @Description: 统一返回结果类
 * @since 2022-08-01
 */
@Data
@ApiModel(
        value = "接口返回对象",
        description = "接口返回对象"
)
public class Result<T> implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(Result.class);

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer code;

    /**
     * 返回状态信息（ 成功|失败）
     */
    @ApiModelProperty("返回状态信息")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    public Result(){}


    /**
     * 封装成功方法
     */
    public static<T> Result<T> success(Integer code,String message,T data){
        Result<T> result = new Result<>();
        if(data != null){
            result.setData(data);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static<T> Result<T> success(){
        return success(20000,"成功",null);
    }
    public static<T> Result<T> success(String message){
        return success(20000,message,null);
    }
    public static<T> Result<T> success(T data){
        return success(20000,"成功",data);
    }
    public static<T> Result<T> success(String message ,T data){
        return success(20000,message,data);
    }

    /**
     * 失败方法
     */
    public static<T> Result<T> error(Integer code,String message,T data){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static<T> Result<T> error(ResultCodeEnum codeEnum){
        return error(codeEnum.getCode(), codeEnum.getMessage(), null);
    }

    /**
     * 自定义异常
     * code 不能为空
     */
    public static<T> Result<T> error(Integer code,String message){
        return error(code,message,null);
    }

    public static<T> Result<T> error(String message){
        return error(500,message,null);
    }


    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}
