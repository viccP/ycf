package com.ycf.utils;

import java.security.MessageDigest;

public class MD5 {
	public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static String getHash(String msg, String hashType) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(hashType);
		md5.update(msg.getBytes());
		return toHexString(md5.digest());
	}

	/**
	 * 加密过后的字母大写
	 */
	public static String getHash(String msg) throws Exception {
		return getHash(msg,"MD5");
	}
}
