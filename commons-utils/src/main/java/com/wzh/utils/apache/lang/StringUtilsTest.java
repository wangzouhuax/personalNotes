package com.wzh.utils.apache.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author wangzouhuax
 */
public class StringUtilsTest {

    /**
     * 判断String 是否为 null/""/"   "
     */
    @Test
    public void test1() {
        String str = "";
        System.out.println(StringUtils.isBlank(str));
        // 反过来
        System.out.println(StringUtils.isNotBlank(str));
    }

    /**
     * 判断String 是否为 null/""
     */
    @Test
    public void test2() {
        String str = "";
        System.out.println(StringUtils.isEmpty(str));
        // 反过来
        System.out.println(StringUtils.isNotEmpty(str));
    }

    /**
     * 12345678911
     * 脱敏
     * 123****8911
     */
    @Test
    public void test3() {
        String str = "12345678911";
        // 返回某个字符串左边的几个字符
        String left = StringUtils.left(str, 3); // 123
        String right = StringUtils.right(str, 4);// 8911
        System.out.println(left + "****" + right);
        // rightPad: 如Left长度<7,那么就在右边用*填充到7个长度
        String padResult = StringUtils.rightPad(left, 7, '*');
        System.out.println(padResult + right);
    }


}
