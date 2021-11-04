package com.ace.springjdbcdemo.service;

import org.springframework.http.ResponseEntity;

import com.ace.springjdbcdemo.vo.GetPhoneResponse;
import com.ace.springjdbcdemo.vo.PhoneRequest;

public interface PhoneService {
public GetPhoneResponse findPhoneByEmpId(String employeeId,boolean getExpired);
	
    public void savePhone(PhoneRequest PhoneRequest,String employeeId);
	
	public void updatePhone(PhoneRequest PhoneRequest,String employeeId);
	
	public void expirePhone(String employeeId) ;
}
