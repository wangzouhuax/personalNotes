package com.wzh.strategy;

/**
 * 抽象类
 * 其他三方渠道都继承于抽象类
 * @author wangzouhuax
 */
public abstract class PayChannel {

    /**
     * 抽象方法，根据不同渠道进行处理
     */
    public abstract void process();

}
