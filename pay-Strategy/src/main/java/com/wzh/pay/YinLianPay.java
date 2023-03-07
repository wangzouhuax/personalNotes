package com.wzh.pay;

import com.wzh.strategy.PayChannel;

public class YinLianPay extends PayChannel {
    @Override
    public void process() {
        System.out.println("============>> 银联支付渠道    >> ");
    }
}
