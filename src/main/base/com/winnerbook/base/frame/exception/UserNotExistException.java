package com.winnerbook.base.frame.exception;

public class UserNotExistException extends Exception {
	private static final long serialVersionUID = -7906432764056459971L;
	/**
	 * 构造函数
	 */
	public UserNotExistException() {
	}
	/**
	 *构造函数
	 *@param message
	 */
	public UserNotExistException(String message) {
		super(message);
	}
	/**
	 *构造函数
	 *@param cause
	 */
	public UserNotExistException(Throwable cause) {
		super(cause);
	}
	/**
	 *构造函数
	 *@param message
	 *@param  cause
	 */
	public UserNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
