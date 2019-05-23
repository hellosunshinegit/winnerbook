/** 
 * 2015-3-19
 * ConstantUtils.java
 */
package com.winnerbook.base.common.util;

import java.util.ResourceBundle;
/**
 * 获取common.properties 配置文件中的数据
 * @author Administrator
 *
 */
public class ConstantUtils {
	private static ResourceBundle rb = ResourceBundle.getBundle("common");
	public static String initial_password = rb.getString("initial_password");
	public static String FILE_SERVER_LOCAL_PATH = rb.getString("FILE_SERVER_LOCAL_PATH");
	public static String SERVER_LOCAL_PATH = rb.getString("SERVER_LOCAL_PATH");
	public static String QROCDE_HTTP = rb.getString("QROCDE_HTTP");
	public static String BASE_PATH_URL = rb.getString("BASE_PATH_URL");
	public static String H5_URL = rb.getString("H5_URL");//后台点击预览h5
	
	public static void main(String[] args) {
		System.out.println(rb.getString("initial_password"));
	}
}
