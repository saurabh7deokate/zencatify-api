package com.zencatify.exception.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zencatify.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + ex.getMessage());
    }

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(UserNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PasswordMissMatchException.class)
	public ResponseEntity<ResponseStructure<String>> handle(PasswordMissMatchException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(UserIdNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handle(InvalidCredentialsException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SamePasswordException.class)
	public ResponseEntity<ResponseStructure<String>> handle(SamePasswordException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>(exception.getMessage(),
				HttpStatus.NOT_FOUND.value(), exception.getMessage() + ", Please try again");
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
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
}
