package com.ace.springjdbcdemo.service;

import com.ace.springjdbcdemo.vo.GetEmployeeFullDetailsResponse;

public interface EmployeeFullDetailsService {

	public GetEmployeeFullDetailsResponse getEmployeeFullDetails(boolean getExpired,String employeeId);
}
