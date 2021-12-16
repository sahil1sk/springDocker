package com.infosys.infytel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.infytel.dto.CallDetailsDTO;
import com.infosys.infytel.dto.CustomerDTO;
import com.infosys.infytel.dto.FriendFamilyDTO;
import com.infosys.infytel.dto.LoginDTO;
import com.infosys.infytel.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CustomerService custService;

	@PostMapping("/customers")
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
		logger.info("Inside createCustomer()");
		logger.info("Creation request for customer {}", custDTO);
		custService.createCustomer(custDTO);
	}

	@PostMapping("/login")
	public boolean login(@RequestBody LoginDTO loginDTO) {
		logger.info("Inside login()");
		logger.info("Login request for customer {} with password {}", loginDTO.getPhoneNo(), loginDTO.getPassword());
		return custService.login(loginDTO);
	}

	@GetMapping("/customers/{phoneNo}")
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {
		logger.info("Inside getCustomerProfile()");
		logger.info("Profile request for customer {}", phoneNo);
		return custService.getCustomerProfile(phoneNo);
	}

	@GetMapping("/customers/{phoneNo}/calldetails")
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {
		logger.info("Inside getCustomerCallDetails()");
		logger.info("Calldetails request for customer {}", phoneNo);
		return custService.getCustomerCallDetails(phoneNo);
	}

	@PostMapping("/customers/{phoneNo}/friends")
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		logger.info("Inside saveFriend()");
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		custService.saveFriend(phoneNo, friendDTO);
	}
}