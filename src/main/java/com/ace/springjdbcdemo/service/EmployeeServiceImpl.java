package com.ace.springjdbcdemo.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.springjdbcdemo.common.ApplicationUtils;
import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.repo.EmployeeRepo;
import com.ace.springjdbcdemo.vo.GetNewEmployeeIdResponse;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	
	@Override
	public GetNewEmployeeIdResponse getNewEmployeeId() {
		BigInteger employeeId =employeeRepo.getNewEmployeeId();
		GetNewEmployeeIdResponse GetNewEmployeeIdResponse = new GetNewEmployeeIdResponse(employeeId.toString());
		return GetNewEmployeeIdResponse;
	}


	@Override
	public void expireEmployee(String employeeId) {
		ApplicationUtils.validateEmployeeId(employeeId);
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int NoOfRowEffected=employeeRepo.expireEmployee(bigIntemployeeId);
		
		if(NoOfRowEffected==0) {
			throw new RecordNotFound("employee not found");
		}
		
	}

}
