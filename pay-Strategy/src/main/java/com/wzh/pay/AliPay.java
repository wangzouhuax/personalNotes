package com.wzh.pay;

import com.wzh.strategy.PayChannel;

public class AliPay extends PayChannel {
    @Override
    public void process() {
        System.out.println("============>> 支付宝支付渠道  >>");
    }
}
