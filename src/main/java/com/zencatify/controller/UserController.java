package com.zencatify.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zencatify.util.GenericResponse.GenericResponseBuilder;
import com.zencatify.model.User;
import com.zencatify.service.UserService;
import com.zencatify.util.GenericResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// TODO: check if this the right place to declare variables that needs to be
	// used at many places?
	private static final String USER_REGISTER_SUCCESS_MESSAGE;
	private static final String USER_REGISTER_FAIL_MESSAGE;
	private static final String USER_LOGIN_SUCCESS_MESSAGE;

	static {
		USER_REGISTER_SUCCESS_MESSAGE = "User registered successfully";
		USER_REGISTER_FAIL_MESSAGE = "Failed to register user";
		USER_LOGIN_SUCCESS_MESSAGE = "User login Successful";
	}

	@PostMapping("/register")
	public ResponseEntity<GenericResponse<String>> registerUser(@Valid @RequestBody @NotNull User user) {
		logger.info("Initial user registration request body: {}", user);
		userService.registerUser(user);
		GenericResponse<String> genericResponse = new GenericResponseBuilder<String>()
				.setMessage(USER_REGISTER_SUCCESS_MESSAGE).setStatus(HttpStatus.OK.value()).build();
		logger.info("User registered successfully: {}", user);
		return ResponseEntity.ok(genericResponse);
	}

	@GetMapping("/login")
	public ResponseEntity<GenericResponse<String>> loginUser(@RequestParam String userName,
			@RequestParam String userPassword) {
		logger.info("Initial user login request body: {}", userName, userPassword);
		userService.loginUser(userName, userPassword);
		GenericResponse<String> genericResponse = new GenericResponseBuilder<String>()
				.setMessage(USER_LOGIN_SUCCESS_MESSAGE).setStatus(HttpStatus.OK.value()).build();

		logger.info("User login successful: {}", userName);
		return ResponseEntity.ok(genericResponse);
	}

//	@PutMapping("/update")
//	public ResponseEntity<GenericResponse> updateUser(@RequestParam String userName,
//			@RequestParam String userPassword, @RequestBody User user) {
//
//		return userService.updateUser(userName, userPassword, user);
//	}
//
//	@DeleteMapping("/delete")
//	public ResponseEntity<GenericResponse> deleteUser(@RequestParam String userName,
//			@RequestParam String userPassword) {
//
//		return userService.deleteUser(userName, userPassword);
//	}
//
//	@PatchMapping("/update-password")
//	public ResponseEntity<GenericResponse> updateUserPassword(@RequestParam String userName,
//			@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String newPasswordAgain) {
//
//		return userService.updateUserPassword(userName, oldPassword, newPassword, newPasswordAgain);
//	}

}
