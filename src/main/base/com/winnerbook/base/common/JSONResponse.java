package com.winnerbook.base.common;

import java.io.Serializable;

public class JSONResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2795163213543713867L;

	private boolean success = false;

	private String msg;

	private Object data;

	private String url;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
