package com.infosys.infytel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.infytel.dto.PlanDTO;
import com.infosys.infytel.service.PlanService;

@RestController
@CrossOrigin
public class PlanController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PlanService planService;

	@GetMapping("/plans")
	public List<PlanDTO> getAllPlans() {
		logger.info("Inside getAllPlans()");
		return planService.getAllPlans();
	}
}