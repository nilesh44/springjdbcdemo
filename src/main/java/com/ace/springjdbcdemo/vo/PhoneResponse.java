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
public class PhoneResponse {

	private String phoneId;
	private String phoneNumber;
	private boolean prefered;
	private Timestamp createTimestamp;
	private Timestamp expireTimeStamp;
}
