package com.ace.springjdbcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.service.EmailService;
import com.ace.springjdbcdemo.vo.EmailRequest;
import com.ace.springjdbcdemo.vo.GetEmailResponse;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	   
	//http://localhost:8090/email/6?getExpired=true
	@GetMapping("/email/{employeeId}")
	public ResponseEntity<Object> getemailById(@RequestParam boolean getExpired,@PathVariable String employeeId) {
		GetEmailResponse getEmailResponse = emailService.findEmailByEmpId(employeeId,getExpired);
		if (getEmailResponse != null) {
			return new ResponseEntity<>(getEmailResponse, HttpStatus.OK);
		}
		throw new RecordNotFound("email not found");
	}

	
	// http://localhost:8090/email/2
	@PostMapping("/email/{employeeId}")
	public ResponseEntity<Object> createEmail(@PathVariable String employeeId,
			@RequestBody EmailRequest emailRequest) {
		emailService.saveEmail(emailRequest, employeeId);
		return ResponseEntity.noContent().build();

	}

	// http://localhost:8090/email/2
	@PutMapping("/email/{employeeId}")
	public ResponseEntity<Object> updateEmail(@PathVariable String employeeId,
			@RequestBody EmailRequest emailRequest) {
		emailService.updateEmail(emailRequest, employeeId);
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping("/email/{employeeId}")
	public ResponseEntity<Object> expireEmail(@PathVariable String employeeId) {
		emailService.expireEmail(employeeId);
		return ResponseEntity.noContent().build();

	}
}
