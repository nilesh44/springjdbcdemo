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
import com.ace.springjdbcdemo.service.PhoneService;
import com.ace.springjdbcdemo.vo.GetPhoneResponse;
import com.ace.springjdbcdemo.vo.PhoneRequest;
@RestController
public class PhoneController {

	@Autowired
	private PhoneService phoneService;

	//http://localhost:8090/phone/6?getExpired=true
	@GetMapping("/phone/{employeeId}")
	public ResponseEntity<Object> getPhoneById(@RequestParam boolean getExpired,@PathVariable String employeeId) {
		GetPhoneResponse getPhoneResponse =  phoneService.findPhoneByEmpId(employeeId,getExpired);
		if (getPhoneResponse != null) {
			return new ResponseEntity<>(getPhoneResponse, HttpStatus.OK);
		}
		throw new RecordNotFound("phone not found");
	}

	// http://localhost:8090/phone/2
	@PostMapping("/phone/{employeeId}")
	public ResponseEntity<Object> createPhone(@PathVariable String employeeId,
			@RequestBody PhoneRequest phoneRequest) {
		phoneService.savePhone(phoneRequest, employeeId);
		return ResponseEntity.noContent().build();

	}

	// http://localhost:8090/phone/2
	@PutMapping("/phone/{employeeId}")
	public ResponseEntity<Object> updatePhone(@PathVariable String employeeId,
			@RequestBody PhoneRequest phoneRequest) {
		phoneService.updatePhone(phoneRequest, employeeId);
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping("/phone/{employeeId}")
	public ResponseEntity<Object> expirePhone(@PathVariable String employeeId) {
		phoneService.expirePhone(employeeId);
		return ResponseEntity.noContent().build();

	}
}
