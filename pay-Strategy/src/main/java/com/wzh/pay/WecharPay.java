package com.wzh.pay;

import com.wzh.strategy.PayChannel;

public class WecharPay extends PayChannel {
    @Override
    public void process() {
        System.out.println("============>> 微信支付渠道    >> ");
    }
}
