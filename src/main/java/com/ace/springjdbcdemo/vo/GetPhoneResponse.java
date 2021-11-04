package com.ace.springjdbcdemo.vo;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPhoneResponse {
	
	private BigInteger employeeId;
	
	private List<PhoneResponse> phones;
}
