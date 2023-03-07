package com.wzh.utils.apache.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * @author wangzouhuax
 */
public class ArrayUtilsTest {

    /**
     * 给已有数组添加元素
     */
    @Test
    public void test1(){
        int[] ints = new int[1];
        ints[0] = 3;
        // toString打印数组内容
        System.out.println(ArrayUtils.toString(ints));
        // copyArrayGrow()拷贝数组

        // add() 会自动帮你创建新的数组
        int[] newArr = ArrayUtils.add(ints, 7);
        System.out.println(ArrayUtils.toString(ints));
        System.out.println(ArrayUtils.toString(newArr));
    }
}
