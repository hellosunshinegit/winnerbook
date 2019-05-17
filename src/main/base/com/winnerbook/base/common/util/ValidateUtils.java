package com.winnerbook.base.common.util;

import java.util.regex.Pattern;

public class ValidateUtils {
	
	public static final String REGEX_MOBILE = "^1\\d{10}$";

    /**
     * 校验手机号
     * @param phone
     * @return
     *  true   校验成功
     *  false  校验失败
     */
	 public static boolean checkPhone(String phone){
        return Pattern.matches(REGEX_MOBILE,phone);
    }

}
