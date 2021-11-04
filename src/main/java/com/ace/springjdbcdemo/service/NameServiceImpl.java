package com.ace.springjdbcdemo.service;

import static com.ace.springjdbcdemo.common.ApplicationUtils.validateEmployeeId;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.exceptions.RecordPresent;
import com.ace.springjdbcdemo.repo.NameRepo;
import com.ace.springjdbcdemo.vo.Employee;
import com.ace.springjdbcdemo.vo.GetNameResponse;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class NameServiceImpl implements NameService {

	@Autowired
	private NameRepo nameRepo;

	@Override
	public GetNameResponse findByEmpId(String employeeId,boolean getExpired) {
		
		validateEmployeeId(employeeId);
		
		return nameRepo.findByEmpId(new BigInteger(employeeId),getExpired);
		
	}

	@Override
	public void saveEmployeeName(Employee employee ,String employeeId) {
		
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
	
		GetNameResponse getNameResponse= nameRepo.findByEmpId(bigIntemployeeId,false);
			 
		if(getNameResponse==null) {
			nameRepo.saveEmployeeName(employee,bigIntemployeeId);
			log.info("employee name created successfully");
		}else {
			throw new RecordPresent("employee has name present");
		}
		
		
	}

	@Override
	public void updateEmployeeName(Employee employee,String employeeId) {
		
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int noOfRowEffected = nameRepo.updateEmployeeName(employee, bigIntemployeeId);
		
		if(noOfRowEffected ==0) {
			throw new RecordNotFound("name not found");
		}
		log.info("employee name updated successfully");
		
	}
	
	@Override
	public void expireName(String employeeId) {
		
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int noOfRowEffected=nameRepo.expireEmployee(bigIntemployeeId);	
		if(noOfRowEffected ==0) {
			throw new RecordNotFound("name not found");
		}
		log.info(" employee name expired successfully");
	}
	

	
}
