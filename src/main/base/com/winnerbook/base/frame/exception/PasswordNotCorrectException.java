package com.winnerbook.base.frame.exception;


public class PasswordNotCorrectException extends Exception {

	private static final long serialVersionUID = -673981269957579443L;
	/**
	 *构造函数
	 *@param cause
	 */
	public PasswordNotCorrectException(Throwable cause) {
		super(cause);
	}
	/**
	 *构造函数
	 *@param message
	 *@param cause
	 */
	public PasswordNotCorrectException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 *构造函数
	 *@param message
	 */
	public PasswordNotCorrectException(String message) {
		super(message);
	}
	/**
	 *构造函数
	 */
	public PasswordNotCorrectException() {
	}

}
