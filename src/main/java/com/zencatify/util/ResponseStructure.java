package com.zencatify.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;

	public ResponseStructure(String message, int status, T data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public ResponseStructure(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}

	public static <T> ResponseEntity<ResponseStructure<T>> setUserResponse(String meassage, HttpStatus status, T data) {

		ResponseStructure<T> responseStructure = new ResponseStructure<T>(meassage, status.value(), data);
		return new ResponseEntity<>(responseStructure, status);
	}
	
	public static <T> ResponseEntity<ResponseStructure<T>> setUserResponse(String meassage, HttpStatus status) {

		ResponseStructure<T> responseStructure = new ResponseStructure<T>(meassage, status.value());
		return new ResponseEntity<>(responseStructure, status);
	}
}
