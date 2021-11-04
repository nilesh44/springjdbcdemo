package com.ace.springjdbcdemo.common;

import org.apache.commons.lang3.StringUtils;

import com.ace.springjdbcdemo.exceptions.InvalidRequestParameterException;

public class ApplicationUtils {
	
	 public static void validateEmployeeId(String employeeId) {
		  if(!StringUtils.isNumeric(employeeId)) {
			  throw new InvalidRequestParameterException("employeeId should be numeric");
		  }
	  }
	 
	 public static boolean convertCharToBoolen(String c) {
		 if(c==null) {
			 return false;
		 }
		if( c.equalsIgnoreCase("Y")) {
			return true;
		}else if(c.equalsIgnoreCase("N")) {
			return false;
		}else {
			return false;
		}
		 
	 }
}
