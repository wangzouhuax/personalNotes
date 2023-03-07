package com.wzh.utils.apache.lang;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

/**
 * @author wangzouhuax
 */
public class ObjectUtilsTest {

    /**
     * str1,str2,str3....取第一个不为空的作为结果使用
     */
    @Test
    public void test1() {
        String str1 = null;
        String str2 = null;
        String str3 = null;
        System.out.println(ObjectUtils.firstNonNull(str1, str2, str3));
    }

    /**
     * System.identityHashCode(obj):不管obj所在的类有没有重写hashcode方法,
     * identityHashCode始终调用的都是Object.hashCode()
     */
    @Test
    public void test2() {
        // java.lang.String@515f550a
        System.out.println(ObjectUtils.identityToString("abc"));
    }


}
