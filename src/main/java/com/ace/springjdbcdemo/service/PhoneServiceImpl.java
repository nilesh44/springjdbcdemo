package com.ace.springjdbcdemo.service;

import static com.ace.springjdbcdemo.common.ApplicationUtils.validateEmployeeId;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.exceptions.RecordPresent;
import com.ace.springjdbcdemo.repo.PhoneRepo;
import com.ace.springjdbcdemo.vo.GetPhoneResponse;
import com.ace.springjdbcdemo.vo.PhoneRequest;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	PhoneRepo phoneRepo;
	

	@Override
	public GetPhoneResponse findPhoneByEmpId(String employeeId, boolean getExpired) {
		 
		validateEmployeeId(employeeId);
		
		return phoneRepo.findPhoneByEmpId(new BigInteger(employeeId),getExpired);
		
		
	}

	@Override
	public void savePhone(PhoneRequest phoneRequest, String employeeId) {
        
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		GetPhoneResponse getPhoneResponse =phoneRepo.findPhoneByEmpId(new BigInteger(employeeId),false);
		
		if(getPhoneResponse!=null) {
			
			if(getPhoneResponse.getPhones().size()==1) {
				phoneRepo.updatePhone(phoneRequest, bigIntemployeeId);	
				log.info("phone created successfully");
			}
			
		}else {
			phoneRepo.savePhone(phoneRequest,bigIntemployeeId);
			log.info("phone created successfully");
		}
		
		
	}

	@Override
	public void updatePhone(PhoneRequest phoneRequest, String employeeId) {
      
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int noOfRowEffected = phoneRepo.updatePhone(phoneRequest, bigIntemployeeId);
		if(noOfRowEffected ==0) {
			throw new RecordNotFound("phone not found");
		}
		log.info("phone name updated successfully");
	}

	@Override
	public void expirePhone(String employeeId) {
      
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int noOfRowEffected =	phoneRepo.expirePhone(bigIntemployeeId);	
		
		if(noOfRowEffected ==0) {
			throw new RecordNotFound("phone not found");
		}
		log.info("phone expired successfully");
		
	}

}
