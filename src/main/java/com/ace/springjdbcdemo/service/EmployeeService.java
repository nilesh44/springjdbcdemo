package com.ace.springjdbcdemo.service;

import com.ace.springjdbcdemo.vo.GetNewEmployeeIdResponse;

public interface EmployeeService {
	
	public GetNewEmployeeIdResponse getNewEmployeeId();
	
	public void expireEmployee(String employeeId) ;
}
