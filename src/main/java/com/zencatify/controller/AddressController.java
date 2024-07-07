//package com.zencatify.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zencatify.model.Address;
//import com.zencatify.service.AddressService;
//import com.zencatify.util.GenericResponse;
//
//@RestController
//@RequestMapping("/address")
//public class AddressController {
//
//	@Autowired
//	public AddressService addressService;
//
//	@PostMapping("/add")
//	public ResponseEntity<GenericResponse<Address>> addAddress(@RequestParam Integer userId, @RequestBody Address address) {
//
//		return addressService.addAddress(userId, address);
//	}
//}
