package com.zencatify.exception.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.zencatify.exception.InvalidCredentialsException;
import com.zencatify.exception.PasswordMissMatchException;
import com.zencatify.exception.SamePasswordException;
import com.zencatify.exception.UserIdNotFoundException;
import com.zencatify.exception.UserNotFoundException;
import com.zencatify.util.GenericResponse;
import com.zencatify.util.GenericResponse.GenericResponseBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private static <T> GenericResponse<T> setResponse(Exception exception, HttpStatus httpStatus, T data) {
		GenericResponse<T> errorResponse = new GenericResponseBuilder<T>().setMessage(exception.getMessage())
				.setStatus(httpStatus.value()).setData(data).build();
		return errorResponse;
	}

	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> map = new HashMap<>();

		for (ObjectError errors : allErrors) {
			FieldError error = (FieldError) errors;
			map.put(error.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handle(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error " + ex.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<GenericResponse<String>> handle(UserNotFoundException exception) {
		GenericResponse<String> errorResponse = GlobalExceptionHandler.setResponse(exception, HttpStatus.NOT_FOUND,
				exception.getMessage() + ", please try again");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PasswordMissMatchException.class)
	public ResponseEntity<GenericResponse<String>> handle(PasswordMissMatchException exception) {
		GenericResponse<String> errorResponse = GlobalExceptionHandler.setResponse(exception, HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage() + ", please try again");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<GenericResponse<String>> handle(UserIdNotFoundException exception) {
		GenericResponse<String> errorResponse = GlobalExceptionHandler.setResponse(exception, HttpStatus.NOT_FOUND,
				exception.getMessage() + ", please try again");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<GenericResponse<String>> handle(InvalidCredentialsException exception) {
		GenericResponse<String> errorResponse = GlobalExceptionHandler.setResponse(exception, HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage() + ", please try again");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(SamePasswordException.class)
	public ResponseEntity<GenericResponse<String>> handle(SamePasswordException exception) {
		GenericResponse<String> errorResponse = GlobalExceptionHandler.setResponse(exception, HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage() + ", please try again");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}
