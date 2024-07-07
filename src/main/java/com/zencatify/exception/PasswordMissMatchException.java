package com.zencatify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
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
