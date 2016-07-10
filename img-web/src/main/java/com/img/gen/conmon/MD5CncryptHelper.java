package com.img.gen.conmon;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5 加密
 * @author liuyunfei
 *
 */
public class MD5CncryptHelper {
    public static String cncryptMD5(String password) {
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            String pwd = new BigInteger(1, md.digest()).toString(32).toUpperCase();
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}
