package com.ace.springjdbcdemo.service;

import static com.ace.springjdbcdemo.common.ApplicationUtils.validateEmployeeId;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.repo.EmailRepo;
import com.ace.springjdbcdemo.vo.EmailRequest;
import com.ace.springjdbcdemo.vo.GetEmailResponse;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	EmailRepo emailRepo;

	@Override
	public GetEmailResponse findEmailByEmpId(String employeeId, boolean getExpired) {
	    
		validateEmployeeId(employeeId);
		
		 return emailRepo.findByEmpId(new BigInteger(employeeId),getExpired);
		
	}

	@Override
	public void saveEmail(EmailRequest emailRequest, String employeeId) {
		
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		GetEmailResponse getEmailResponse=emailRepo.findByEmpId(new BigInteger(employeeId),false);
		
		if(getEmailResponse!=null) {
			
			emailRepo.updateEmail(emailRequest, bigIntemployeeId);
		
		}else {
		emailRepo.saveEmail(emailRequest,bigIntemployeeId);
		}
		log.info("email created successfully");
		
	}

	@Override
	public void updateEmail(EmailRequest emailRequest, String employeeId) {
		
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int noOfRowEffected = emailRepo.updateEmail(emailRequest, bigIntemployeeId);
		
		if(noOfRowEffected ==0) {
			throw new RecordNotFound("email not found");
		}
		log.info("email name updated successfully");
		
	}

	@Override
	public void expireEmail(String employeeId) {
       
		validateEmployeeId(employeeId);
		
		BigInteger	bigIntemployeeId=new BigInteger(employeeId);
		
		int noOfRowEffected =emailRepo.expireEmail(bigIntemployeeId);	
		
		if(noOfRowEffected ==0) {
			throw new RecordNotFound("email not found");
		}
		log.info("email expired successfully");
		
	}

	
	
}
