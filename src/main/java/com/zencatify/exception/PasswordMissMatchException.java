package com.zencatify.exception;

public class PasswordMissMatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public PasswordMissMatchException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}

}
