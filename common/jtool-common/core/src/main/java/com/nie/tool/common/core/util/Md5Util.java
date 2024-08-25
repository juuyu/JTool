package com.nie.tool.common.core.util;

import org.dromara.hutool.crypto.digest.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具(是基于hash算法实现,不可逆)
 *
 * @author njy
 * @since 2023/2/16 17:33
 */
public class Md5Util {


    /**
     * 16进制的字符数组
     */
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};


    public static String MD5Encode(String source) {
        return MD5Encode(source, null,"utf-8", false);
    }

    public static String MD5EncodeUseSalt(String source, String salt) {
        return MD5Encode(source, salt,"utf-8", false);
    }


    // source 需要加密的原字符串
    // encoding 指定编码类型
    // uppercase 是否转为大写字符串 true为大写   false为小写
    public static String MD5Encode(String source, String salt, String encoding, boolean uppercase) {
        String result = null;
        try {
            result = source + salt;
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            messageDigest.update(result.getBytes(encoding));
            // messageDigest.digest()获得16位长度
            result = byteArrayToHexString(messageDigest.digest());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uppercase ? result.toUpperCase() : result;
    }


    /**
     * 转换字节数组为16进制字符串
     *
     * @param bytes 字节数组
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    /**
     * 转换byte到16进制
     *
     * @param b 要转换的byte
     * @return 16进制对应的字符
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    public static String md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            // buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return result.toUpperCase();
    }

    public static void main(String[] args) {
        // a448410bdcbb4d7cfb32830909f6aa08

        MD5 of = MD5.of();

        System.out.println(md5("niejunyu"));
        System.out.println(MD5.of().digestHex("niejunyu"));
    }


}
