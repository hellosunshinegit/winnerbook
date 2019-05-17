package com.winnerbook.base.common;

import java.io.Serializable;

public class MobileResponse implements Serializable {

	private static final long serialVersionUID = -7369026722950978824L;

	private int code;
	
	private Object data;
	
	private String error = "";
	
	/**成功**/
	public static final int SUCCESS = 200;
	/**信息不全**/
	public static final int INCOMPLETE = 201;
	/**失败**/
	public static final int FAILURE = 202;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
