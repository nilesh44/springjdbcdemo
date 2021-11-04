package com.ace.springjdbcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.service.EmployeeFullDetailsService;
import com.ace.springjdbcdemo.vo.GetEmployeeFullDetailsResponse;

@RestController
public class EmployeeFullDetailsController {

	
	
	@Autowired
	EmployeeFullDetailsService employeeFullDetailsService;
	
	@GetMapping("/fullDetails/{employeeId}")
	public ResponseEntity<Object> getEmployeeFullDetails(@RequestParam boolean getExpired,@PathVariable String employeeId) {
		GetEmployeeFullDetailsResponse fullDetails=	employeeFullDetailsService.getEmployeeFullDetails(getExpired,employeeId);
		if (fullDetails != null) {
			return new ResponseEntity<>(fullDetails, HttpStatus.OK);
		}
		throw new RecordNotFound("employee details not found");
	}
}																																	
