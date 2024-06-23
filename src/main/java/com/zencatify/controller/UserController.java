package com.zencatify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zencatify.model.User;
import com.zencatify.service.UserService;
import com.zencatify.util.ResponseStructure;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody @NonNull User user) {
		try {
            userService.registerUser(user);
            return ResponseEntity.ok().body("{\"message\": \"User registered successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Failed to register user\"}");
        }
	}

	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String userName,
			@RequestParam String userPassword) {

		return userService.loginUser(userName, userPassword);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<String>> updateUser(@RequestParam String userName,
			@RequestParam String userPassword, @RequestBody User user) {

		return userService.updateUser(userName, userPassword, user);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@RequestParam String userName,
			@RequestParam String userPassword) {

		return userService.deleteUser(userName, userPassword);
	}

	@PatchMapping("/update-password")
	public ResponseEntity<ResponseStructure<User>> updateUserPassword(@RequestParam String userName,
			@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String newPasswordAgain) {

		return userService.updateUserPassword(userName, oldPassword, newPassword, newPasswordAgain);
	}
}
