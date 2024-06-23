package com.zencatify.exception;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidCredentialsException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}

}
