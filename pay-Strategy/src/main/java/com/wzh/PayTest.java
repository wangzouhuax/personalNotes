package com.wzh;

import com.wzh.channel.PayChannelEnum;
import com.wzh.pay.AliPay;
import com.wzh.pay.YinLianPay;

/**
 * @author wangzouhuax
 * 测试支付
 */
public class PayTest {

    public static void main(String[] args) {
        // 展示问题-----------------
        String channelName = "AliPay";
        /*if (channelName.equals("AliPay")) {
            new AliPay().process();
        } else if (channelName.equals("YinLianPay")) {
            new YinLianPay().process();
        } else if (channelName.equals("WecharPay")) {
            new YinLianPay().process();
        }*/
        // 抽取解决
        // ① 通过枚举类解决
        PayChannelEnum matchChannel = PayChannelEnum.match(channelName);
        if (matchChannel != null) {
            matchChannel.payChannel.process();
        }

    }

}
