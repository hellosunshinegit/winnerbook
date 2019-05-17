/*
 * Hash散列值，用于加密及计算校验码
 * 
 */
package com.winnerbook.base.common.util;

import com.winnerbook.base.common.util.md5.MD5;


/**
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class EncryptUtil {
	
	public static String md5(String source) {
		MD5 	md5 = new MD5();
		String	hex;

		md5.Init();
		md5.Update(source);
		hex = md5.asHex();

		return hex;	
	}

	public static String hash(String source) {
		MD5 	md5 = new MD5();
		String	hex;

		md5.Init();
		md5.Update("EA" + source + "CUM");
		hex = md5.asHex();
		System.out.println(hex);
		return hex;	
	}
	public static String hashPay(String source) {
		MD5 	md5 = new MD5();
		String	hex;

		md5.Init();
		md5.Update("PAY" + source + "ken");
		hex = md5.asHex();

		return hex;	
	}
	public static String normalHash(String source) {
		MD5 	md5 = new MD5();
		String	hex;

		md5.Init();
		md5.Update("MEMBER" + source + "i1188");
		hex = md5.asHex();

		return hex;	
	}

	public static String hashAdmin(String source) {
		MD5 	md5 = new MD5();
		String	hex;

		md5.Init();
		md5.Update("COM" + source + "i1188");
		hex = md5.asHex();

		return hex;	
	}
	
	public static void main(String[] args) {
		hash("123456");
	}
}
