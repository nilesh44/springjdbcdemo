package com.ace.springjdbcdemo.utils;

public final class Constant {

	public static final String FIND_BY_NAME_ID = "select * from name"
	        + " where emp_id = :employeeId"
			+ " and exp_tms is null ";
	
	public static final String FIND_BY_NAME_ID_ALL_EXPIRED = "select * from name"
			+ " where emp_id = :employeeId"
			+ " and exp_tms is not null ";
	

}
