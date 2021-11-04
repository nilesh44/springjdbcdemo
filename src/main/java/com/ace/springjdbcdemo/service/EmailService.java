package com.ace.springjdbcdemo.service;

import org.springframework.http.ResponseEntity;

import com.ace.springjdbcdemo.vo.EmailRequest;
import com.ace.springjdbcdemo.vo.GetEmailResponse;

public interface EmailService {

	public GetEmailResponse findEmailByEmpId(String employeeId,boolean getExpired);
	
    public void saveEmail(EmailRequest emailRequest,String employeeId);
	
	public void updateEmail(EmailRequest emailRequest,String employeeId);
	
	public void expireEmail(String employeeId) ;
}
