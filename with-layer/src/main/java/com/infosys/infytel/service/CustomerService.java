package com.infosys.infytel.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.infytel.dto.CallDetailsDTO;
import com.infosys.infytel.dto.CustomerDTO;
import com.infosys.infytel.dto.FriendFamilyDTO;
import com.infosys.infytel.dto.LoginDTO;
import com.infosys.infytel.entity.CallDetails;
import com.infosys.infytel.entity.Customer;
import com.infosys.infytel.entity.FriendFamily;
import com.infosys.infytel.entity.Plan;
import com.infosys.infytel.repository.CallDetailsRepository;
import com.infosys.infytel.repository.CustomerRepository;

@Service
public class CustomerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CustomerRepository custRepo;
	@Autowired
	CallDetailsRepository callDetailsRepo;

	public void createCustomer(CustomerDTO custDTO) {
		logger.info("Creation request for customer {}", custDTO);
		Customer cust = custDTO.createEntity();
		Plan p = new Plan();
		p.setPlanId(custDTO.getCurrentPlan().getPlanId());
		cust.setPlan(p);
		custRepo.save(cust);
	}

	public boolean login(LoginDTO loginDTO) {
		logger.info("Login request for customer {} with password {}", loginDTO.getPhoneNo(), loginDTO.getPassword());
		Customer cust = custRepo.findByPhoneNo(loginDTO.getPhoneNo());
		return cust != null && cust.getPassword().equals(loginDTO.getPassword());
	}

	public CustomerDTO getCustomerProfile(Long phoneNo) {
		logger.info("Profile request for customer {}", phoneNo);
		Customer cust = custRepo.findByPhoneNo(phoneNo);
		CustomerDTO custDTO = CustomerDTO.valueOf(cust);
		logger.info("Profile for customer : {}", custDTO);
		return custDTO;
	}

	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {
		logger.info("Calldetails request for customer {}", phoneNo);
		List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
		List<CallDetailsDTO> callsDTO = new ArrayList<>();
		for (CallDetails call : callDetails) {
			callsDTO.add(CallDetailsDTO.valueOf(call));
		}
		logger.info("Calldetails for customer : {}", callDetails);
		return callsDTO;
	}

	public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		List<FriendFamily> friendList = new ArrayList<>();
		friendList.add(friend);
		Customer c = custRepo.getById(phoneNo);
		c.getFriends().add(friend);
		c.setFriends(c.getFriends());
		custRepo.save(c);
	}
}