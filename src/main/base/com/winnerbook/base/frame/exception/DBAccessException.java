package com.winnerbook.base.frame.exception;

public class DBAccessException extends Exception {

	private static final long serialVersionUID = -3549718163045669656L;

	public DBAccessException() {
		super();
	}

	public DBAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBAccessException(String message) {
		super(message);
	}

	public DBAccessException(Throwable cause) {
		super(cause);
	}

}
