package com.wzh.utils.others;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

    //该方法将你输入的字符串，通过md5加密，返回一个加密後的字符串
    public static String MD5Encrypt(String inStr) throws UnsupportedEncodingException {

        MessageDigest md = null;
        String outStr = null;
        try {
            //可以选中其他的算法如SHA
            md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(inStr.getBytes("utf-8"));
            //返回的是byet[]，要转化为String存储比较方便
            outStr = bytetoString(digest);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }

    private static String bytetoString(byte[] digest) {

        String str = "";
        String tempStr = "";
        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str;
    }

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(MD5Encrypt("16914011531469704060box_active1001100272018-07-13 16:15:042019-07-13 16:15:04201807131615040123456789"));
		System.out.println(MD5Encrypt("1234qwer"));

	}


}
