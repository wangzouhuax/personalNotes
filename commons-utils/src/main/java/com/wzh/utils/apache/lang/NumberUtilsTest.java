package com.wzh.utils.apache.lang;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class NumberUtilsTest {

    /**
     * 判断一个参数是不是数字
     */
    @Test
    public void test1() {
        String str = "12.3aa";
        try {
            System.out.println(Integer.valueOf(str));
        } catch (Exception e) {
            System.out.println();
        }

    }
    /**
     * 判断一个参数是不是数字
     * isDigits()、isParsable() 默认都是当作10进制处理，isCreatable() 与进制有关（以0开头是8进制）
     */
    @Test
    public void test2() {
        String str = "12.3aa";
        // isDigits只能判断整数，也就是参数只能包含数字的时候才返回true
        System.out.println(str + "isDigits结果:" + NumberUtils.isDigits(str)); // false
        // isParsable可以判断是不是整数、浮点数，不能识别正负
        System.out.println(str + "isParsable结果:" + NumberUtils.isParsable(str)); // false
        // isCreatable可以判断是不是整数、浮点数，识别正负号，以及进制
        System.out.println(str + "isCreatable结果:" + NumberUtils.isCreatable(str));
        str = "12.3";
        System.out.println(str + "isDigits结果:" + NumberUtils.isDigits(str)); // false
        System.out.println(str + "isParsable结果:" + NumberUtils.isParsable(str)); // true
        System.out.println(str + "isCreatable结果;" + NumberUtils.isCreatable(str));
        str = "+12.3";
        System.out.println(str + "isDigits结果:" + NumberUtils.isDigits(str)); // false
        System.out.println(str + "isParsable结果:" + NumberUtils.isParsable(str)); // false
        System.out.println(str + "isCreatable结果:" + NumberUtils.isCreatable(str));
        str = "12";
        System.out.println(str + "isDigits结果:" + NumberUtils.isDigits(str)); // true
        System.out.println(str + "isParsable结果:" + NumberUtils.isParsable(str)); // true
        System.out.println(str + "isCreatable结果:" + NumberUtils.isCreatable(str)); // true
        str = "012";
        System.out.println(str + "isDigits结果:" + NumberUtils.isDigits(str)); // true
        System.out.println(str + "isParsable结果:" + NumberUtils.isParsable(str)); // true
        System.out.println(str + "isCreatable结果:" + NumberUtils.isCreatable(str)); // true
    }
}
