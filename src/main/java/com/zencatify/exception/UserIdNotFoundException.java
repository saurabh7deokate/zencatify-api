package com.zencatify.exception;

public class UserIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public UserIdNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}

}
