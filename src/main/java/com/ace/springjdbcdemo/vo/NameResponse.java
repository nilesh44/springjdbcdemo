package com.ace.springjdbcdemo.vo;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class NameResponse {
	
	private String nameId;
	private String firstName;
	private String lastName;
	private Timestamp createTimestamp;
	private Timestamp expireTimeStamp;
}
