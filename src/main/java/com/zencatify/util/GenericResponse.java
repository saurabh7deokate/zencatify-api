package com.zencatify.util;

public class GenericResponse<T> {

	private String message;
	private int status;
	private T data;

	private GenericResponse() {
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public static class GenericResponseBuilder<T> {
		private GenericResponse<T> genericResponse;

		public GenericResponseBuilder() {
			genericResponse = new GenericResponse<T>();
		}

		public GenericResponseBuilder<T> setMessage(String message) {
			genericResponse.message = message;
			return this;
		}

		public GenericResponseBuilder<T> setStatus(int status) {
			genericResponse.status = status;
			return this;
		}

		public GenericResponseBuilder<T> setData(T data) {
			genericResponse.data = data;
			return this;
		}

		public GenericResponse<T> build() {
			return this.genericResponse;
		}
	}
}
