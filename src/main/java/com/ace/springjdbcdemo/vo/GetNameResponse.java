package com.ace.springjdbcdemo.vo;

import java.math.BigInteger;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNameResponse {
	
	private BigInteger employeeId;
	
	private NameResponse names;
	
}
