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
import com.ace.springjdbcdemo.service.NameService;
import com.ace.springjdbcdemo.vo.Employee;
import com.ace.springjdbcdemo.vo.GetNameResponse;

@RestController
public class NameController {

	@Autowired
	private NameService nameService;

	//http://localhost:8090/name/6?getExpired=true
	@GetMapping("/name/{employeeId}")
	public ResponseEntity<Object> getEmployeeById(@RequestParam boolean getExpired,@PathVariable String employeeId) {
		GetNameResponse getNameResponse= nameService.findByEmpId(employeeId,getExpired);
		
		if (getNameResponse != null) {
			return new ResponseEntity<>(getNameResponse, HttpStatus.OK);
		}
		throw new RecordNotFound("name not found");
	}

	// http://localhost:8090/employee/2
	@PostMapping("/name/{employeeId}")
	public ResponseEntity<Object> createEmployeeName(@PathVariable String employeeId,
			@RequestBody Employee employee) {
		nameService.saveEmployeeName(employee, employeeId);
		return ResponseEntity.noContent().build();

	}

	// http://localhost:8090/employee/updateName/2
	@PutMapping("/name/{employeeId}")
	public ResponseEntity<Object> updateEmployeeName(@PathVariable String employeeId,
			@RequestBody Employee employee) {
		nameService.updateEmployeeName(employee, employeeId);
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping("/name/{employeeId}")
	public ResponseEntity<Object> expireEmployeeName(@PathVariable String employeeId) {
		nameService.expireName(employeeId);
		return ResponseEntity.noContent().build();

	}

}
