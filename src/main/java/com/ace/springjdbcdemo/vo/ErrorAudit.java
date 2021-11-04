package com.ace.springjdbcdemo.vo;

import com.google.gson.JsonElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorAudit {


	private String uri;

	private String methodType;

	private String timestamp;
	
	
	private JsonElement body;
	
	private String errorMessge;
}
