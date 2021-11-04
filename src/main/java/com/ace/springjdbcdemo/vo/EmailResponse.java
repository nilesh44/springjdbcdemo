package com.ace.springjdbcdemo.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
	
	private String emailId;
	private String emailAddress;
	private boolean prefered;
	private Timestamp createTimestamp;
	private Timestamp expireTimeStamp;
}
