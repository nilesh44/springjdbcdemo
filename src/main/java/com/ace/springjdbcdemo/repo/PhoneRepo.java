package com.ace.springjdbcdemo.repo;

import java.math.BigInteger;

import com.ace.springjdbcdemo.vo.GetPhoneResponse;
import com.ace.springjdbcdemo.vo.PhoneRequest;

public interface PhoneRepo {

	public GetPhoneResponse findPhoneByEmpId(BigInteger bigInteger,boolean getExpired);
	
	public int savePhone(PhoneRequest phoneRequest,BigInteger employeeId);
	
	public int updatePhone(PhoneRequest phoneRequest,BigInteger employeeId);
	
	public int expirePhone(BigInteger employeeId);
}
