package com.ace.springjdbcdemo.repo;

import java.math.BigInteger;

public interface EmployeeRepo {

	public BigInteger getNewEmployeeId() ;
	
	public int expireEmployee(BigInteger employeeId) ;
	
	
	
}
