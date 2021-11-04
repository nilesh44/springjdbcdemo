package com.ace.springjdbcdemo.repo;

import java.math.BigInteger;

import com.ace.springjdbcdemo.vo.EmailRequest;
import com.ace.springjdbcdemo.vo.GetEmailResponse;

public interface EmailRepo {
	
    public GetEmailResponse findByEmpId(BigInteger bigInteger,boolean getExpired);
	
	public int saveEmail(EmailRequest emailRequest,BigInteger employeeId);
	
	public int updateEmail(EmailRequest emailRequest,BigInteger employeeId);
	
	public int expireEmail(BigInteger employeeId);
}
