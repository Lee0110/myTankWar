package com.Lee.utils;

import java.security.MessageDigest;

public class MD5Tool {
	private static final String[] digital = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
			"f" };

	private static String md5(String password) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String encry = "";
		if (password != null) {
			byte[] bytes = md5.digest(password.getBytes("utf-8"));
			for (byte b : bytes) {
				int num = b;
				// System.out.print(num + " ");
				if (num < 0) {
					num += 256;
				}
				// 每个数字计算出两个下标
				int index1 = num / 16;
				int index2 = num % 16;
				encry += digital[index1] + digital[index2];
			}
		}
		return encry;
	}

	public static String encryByMD5(String txt) throws Exception {
		return md5(txt);
	}
}
