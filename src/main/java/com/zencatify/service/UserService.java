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
import com.zencatify.util.GenericResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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

	public void registerUser(User user) {
		userRepository.save(user);
	}

	public void loginUser(String userName, String userPassword) {
		userAuthorization(userName, userPassword);
	}

	public void updateUser(String userName, String userPassword, User user) {

		User dbUser = userAuthorization(userName, userPassword);
		user.setUserId(dbUser.getUserId());
		userRepository.save(user);
	}

	public void deleteUser(String userName, String userPassword) {

		User user = userAuthorization(userName, userPassword);
		userRepository.delete(user);
	}

	public void updateUserPassword(String userName, String oldPassword,
			String newPassword, String newPasswordAgain) {

		if (!newPassword.equals(newPasswordAgain)) {
			throw new PasswordMissMatchException("New password not matching");
		}
		if (oldPassword.equals(newPasswordAgain)) {
			throw new SamePasswordException("New password cannot be same as old one");
		}
		User user = userAuthorization(userName, oldPassword);
		user.setUserPassword(newPasswordAgain);
		userRepository.save(user);
	}
}
