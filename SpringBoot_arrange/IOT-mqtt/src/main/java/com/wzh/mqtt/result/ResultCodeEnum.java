package com.wzh.mqtt.result;

/**
 * 统一返回结果状态信息类
 * @author wangzouhuax
 */
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    ILLEGAL_REQUEST(205, "非法请求"),
    REPEAT_SUBMIT(206, "重复提交"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    PHONE_CODE_ERROR(211, "手机验证码错误"),

    MTCLOUD_ERROR(210, "直播接口异常"),

    COUPON_GET(220, "优惠券已经领取"),
    COUPON_LIMIT_GET(221, "优惠券已发放完毕"),

    FILE_UPLOAD_ERROR( 21004, "文件上传错误"),
    FILE_DELETE_ERROR( 21005, "文件刪除错误"),

    VOD_PALY_ERROR(209, "请购买后观看"),

    VOD_SAVE_ERROR(210,"添加讲师失败"),
    VOD_UPDATE_ERROR(211,"修改讲师失败"),
    VOD_DELETE_ERROR(212,"删除讲师失败");

    private Integer code;

    private String message;
    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
