package com.wzh.channel;

import com.wzh.pay.AliPay;
import com.wzh.pay.WecharPay;
import com.wzh.pay.YinLianPay;
import com.wzh.strategy.PayChannel;
import org.springframework.util.StringUtils;

/**
 * 枚举类
 * @author wangzouhuax
 */
public enum PayChannelEnum {

    AliPay("AliPay",new AliPay()),

    WecharPay("WecharPay",new WecharPay()),

    YinLianPay("YinLianPay",new YinLianPay());


    public String channelName;
    public PayChannel payChannel;
    PayChannelEnum(String channelName, PayChannel payChannel) {
        this.channelName = channelName;
        this.payChannel = payChannel;
    }

    /**
     * 匹配渠道方法
     * @param channelName
     * @return
     */
    public static PayChannelEnum match(String channelName){
        PayChannelEnum[] values = PayChannelEnum.values();
        for (PayChannelEnum value : values) {
            String name = value.name();
            if (!StringUtils.isEmpty(name) && value.channelName.equals(channelName)) {
                return value;
            }
        }
        return null;

    }



}
