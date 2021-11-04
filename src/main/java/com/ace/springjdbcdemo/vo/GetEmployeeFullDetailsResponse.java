package com.ace.springjdbcdemo.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class GetEmployeeFullDetailsResponse {
	
	private String employeeId;
	private NameResponse names;
	private List<PhoneResponse> phones;
	private List<EmailResponse> emails;
	
	

}
