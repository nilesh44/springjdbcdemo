package com.ace.springjdbcdemo.repo;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j
public class EmployeeRepoImpl implements EmployeeRepo{

	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public BigInteger getNewEmployeeId() {
		
		String INSERT_QUERY =
	            "insert into employee(crt_tms) values(:createTimeStamp)";
		
		 //crerate map of column and value
		 Map<String, Object> values = new HashMap<String, Object>();
	
		 values.put("createTimeStamp",  new Timestamp(System.currentTimeMillis()));
		  
		 //create MapSqlParameterSource as map of input
		 SqlParameterSource parameters = new MapSqlParameterSource(values);
		 
		 //keyholder holds returned generated key from db
		 KeyHolder keyHolder = new GeneratedKeyHolder();
		 
		 namedParameterJdbcTemplate.update(INSERT_QUERY, parameters, keyHolder);
		 
		 return (BigInteger) keyHolder.getKey();
	}

	@Override
	public int expireEmployee(BigInteger employeeId) {
		
		String EXPIRE_QUERY =
	            "update employee set exp_tms = :expirationTimestamp where emp_id = :employeeId and exp_tms is null";
		
		 //crerate map of column and value
		Map<String, Object> values = new HashMap<String, Object>();
		 values.put("employeeId", employeeId);
		 values.put("expirationTimestamp", new Timestamp(System.currentTimeMillis()));
		 
	    SqlParameterSource parameters = new MapSqlParameterSource(values);
		 
		return namedParameterJdbcTemplate.update(EXPIRE_QUERY, parameters);
		
	}

}
