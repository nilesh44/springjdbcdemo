package com.ace.springjdbcdemo.service;

import org.springframework.http.ResponseEntity;

import com.ace.springjdbcdemo.vo.Employee;
import com.ace.springjdbcdemo.vo.GetNameResponse;

public interface NameService {

	public GetNameResponse findByEmpId(String employeeId,boolean getExpired);
	
    public void saveEmployeeName(Employee employee,String employeeId);
	
	public void updateEmployeeName(Employee employee,String employeeId);
	
	public void expireName(String employeeId) ;
	
	
}
