package com.img.gen.conmon;

import java.util.Random;
import java.util.UUID;

/**
 * ID生成器
 * 
 * @author liuyunfei
 *
 */
public final class IdHelper {

	/**
	 * UUID生成(32位)
	 * 
	 * @return
	 */
	public static String generateLongUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}
	
	/**
	 * UUID生成(16位)
	 * @return
	 */
	public static String generateShortUUID() {
		return UUID.randomUUID().toString().replace("-", "").substring(16);
	}

	/**
	 * 生成六位验证码
	 * 
	 * @return
	 */
	public static String getRamdPw6() {
		String ret = "";
		int array[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}

		int result = 0;
		for (int i = 0; i < 6; i++)
			result = result * 10 + array[i];

		if (String.valueOf(result).length() == 5)
			ret = (new StringBuilder("0")).append(result).toString();
		else
			ret = String.valueOf(result);
		return ret;
	}


	private static String randmonString(int length) {
		Random random = new Random();
		String ssource = "0123456789";
		char[] src = ssource.toCharArray();
		char[] buf = new char[length];
		/*    */
		for (int i = 0; i < length; i++) {
			int rnd = Math.abs(random.nextInt()) % src.length;
			buf[i] = src[rnd];
		}
		return new String(buf);
	}
	
	/**
	 * 获取多位随机数
	 * @param i
	 * @return
	 */
	public static String runVerifyCode(int i) {
		String verify = randmonString(i);
		return verify;
	}
}
