package com.zencatify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zencatify.exception.InvalidCredentialsException;
import com.zencatify.exception.PasswordMissMatchException;
import com.zencatify.exception.SamePasswordException;
import com.zencatify.exception.UserNotFoundException;
import com.zencatify.model.User;
import com.zencatify.repository.UserRepository;
import com.zencatify.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private static ResponseEntity<ResponseStructure<String>> setSuccessMesage(String message, HttpStatus status) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>(message, status.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, status);
	}

	public User userAuthorization(String userName, String userPassword) {

		User user = userRepository.findByUserName(userName);
		if (user != null) {
			if (user.getUserPassword().equals(userPassword)) {
				return user;
			} else {
				throw new InvalidCredentialsException("Invalid Password");
			}
		} else {
			throw new UserNotFoundException("User Not Found");
		}
	}

	public User registerUser(User user) {
		return userRepository.save(user);
	}

	public ResponseEntity<ResponseStructure<User>> loginUser(String userName, String userPassword) {
		User user = userAuthorization(userName, userPassword);
		return ResponseStructure.setUserResponse("Login Successful", HttpStatus.FOUND, user);
	}

//	public ResponseEntity<ResponseStructure<User>> findUser(int userId) {
//
//		User user = userRepository.findById(userId).orElse(null);
//		if (user != null) {
//			return setUserResponse("User found Successfully", HttpStatus.FOUND, user);
//		} else {
//			throw new UserNotFoundException("User Not Found");
//		}
//	}

	public ResponseEntity<ResponseStructure<String>> updateUser(String userName, String userPassword, User user) {

		User dbUser = userAuthorization(userName, userPassword);
		user.setUserId(dbUser.getUserId());
		userRepository.save(user);
		return setSuccessMesage("User Updated Succesfully", HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(String userName, String userPassword) {

		User user = userAuthorization(userName, userPassword);
		userRepository.delete(user);
		return setSuccessMesage("User Deleted Successfully", HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> updateUserPassword(String userName, String oldPassword,
			String newPassword, String newPasswordAgain) {
		if (!newPassword.equals(newPasswordAgain)) {
			throw new PasswordMissMatchException("New password not matching");
		}
		if (oldPassword.equals(newPasswordAgain)) {
			throw new SamePasswordException("New password cannot be old one");
		}
		User user = userAuthorization(userName, oldPassword);
		user.setUserPassword(newPasswordAgain);

		return ResponseStructure.setUserResponse("Password Updated Succesfully", HttpStatus.OK,
				userRepository.save(user));
	}
}
