package com.zencatify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zencatify.exception.UserNotFoundException;
import com.zencatify.model.Address;
import com.zencatify.model.User;
import com.zencatify.repository.AddressRepository;
import com.zencatify.repository.UserRepository;
import com.zencatify.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	private static Address checkForDefaultAddress(User dbUser) {
		for (Address address : dbUser.getAddresses()) {
			if (address.getIsUserDefaultAddress() == true)
				return address;
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<Address>> addAddress(Integer userId, Address address) {
		User dbUser = userRepository.findById(userId).orElse(null);
		if (dbUser != null) {
			if (dbUser.getAddresses().size() == 0) {
				address.setIsUserDefaultAddress(true);
			} else {
				if (dbUser.getAddresses().size() != 0 && address.getIsUserDefaultAddress() == true) {
					Address defaultAddress = AddressService.checkForDefaultAddress(dbUser);
					defaultAddress.setIsUserDefaultAddress(false);
					address.setIsUserDefaultAddress(true);
				}
			}
			address.setUser(dbUser);
			Address savedAddress = addressRepository.save(address);
			return ResponseStructure.setUserResponse("Address Saved Successfully", HttpStatus.CREATED, savedAddress);
		} else {
			throw new UserNotFoundException("User with Id " + userId + "does not exist.");
		}
	}

}
