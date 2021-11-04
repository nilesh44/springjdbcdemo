package com.ace.springjdbcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.springjdbcdemo.service.EmployeeService;
import com.ace.springjdbcdemo.vo.GetNewEmployeeIdResponse;

@RestController
public class EmployeeController {
		
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/new/employeeId")
	public ResponseEntity<GetNewEmployeeIdResponse> getNewEmployeeId() {		
		GetNewEmployeeIdResponse getNewEmployeeIdResponse=employeeService.getNewEmployeeId();
		return ResponseEntity.ok(getNewEmployeeIdResponse);
	}	
	
	@DeleteMapping("/expire/employeeId/{employeeId}")
	public ResponseEntity<GetNewEmployeeIdResponse> expireEmployee(@PathVariable String employeeId) {		
		employeeService.expireEmployee(employeeId);
		return ResponseEntity.noContent().build();
	}
}
