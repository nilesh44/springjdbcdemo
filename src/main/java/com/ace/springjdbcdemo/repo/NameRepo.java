package com.ace.springjdbcdemo.repo;

import java.math.BigInteger;

import com.ace.springjdbcdemo.vo.Employee;
import com.ace.springjdbcdemo.vo.GetNameResponse;



public interface NameRepo {

	public GetNameResponse findByEmpId(BigInteger bigInteger,boolean getExpired);
	
	public int saveEmployeeName(Employee employee,BigInteger employeeId);
	
	public int updateEmployeeName(Employee employee,BigInteger employeeId);
	
	public int expireEmployee(BigInteger employeeId);
	
}
