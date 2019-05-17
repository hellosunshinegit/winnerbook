package com.winnerbook.base.common.util;

/**
 * @author hu jinzhong 2014-4-11
 *
 */
public class MessageVerifyCodeModel {
	private String verifyCode;  //验证码
	private String time;        //时间戳
	private String mobile;      //手机号码
	
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
