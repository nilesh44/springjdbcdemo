package com.ace.springjdbcdemo.repo;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.mapper.PhoneRowMapper;
import com.ace.springjdbcdemo.vo.GetPhoneResponse;
import com.ace.springjdbcdemo.vo.PhoneRequest;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j
public class PhoneRepoImpl implements PhoneRepo {
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public GetPhoneResponse findPhoneByEmpId(BigInteger employeeId, boolean getExpired) {
		
		 String sql = "select * from mobile"
			        + " where emp_id = :employeeId"
					+ " and exp_tms is null ";
		 
			if(getExpired) {
				sql = "select * from mobile"
						+ " where emp_id = :employeeId"
						+ " and exp_tms is not null ";
			}
		 
		 
	
			try {
				 GetPhoneResponse response=namedParameterJdbcTemplate
						.queryForObject(
								sql,  
								new MapSqlParameterSource("employeeId", employeeId),
								new PhoneRowMapper());
				response.setEmployeeId(employeeId);
				return response;
			}catch(EmptyResultDataAccessException emptyResultDataAccessException) {
				log.error("email not found",emptyResultDataAccessException);
				return null;
			}
			
	}

	@Override
	public int savePhone(PhoneRequest phoneRequest, BigInteger employeeId) {
		
		//create nameParameter query
		 String INSERT_QUERY =
		            "insert into mobile (emp_id,mob_num,prefered,crt_tms)"
		            + " values( :employeeId , :mobileNumber , :prefered , :createTimeStamp)";
		
		 //crerate map of column and value
		 Map<String, Object> values = new HashMap<String, Object>();
		 values.put("employeeId", employeeId);
		 values.put("mobileNumber",phoneRequest.getPhoneNumber());
		 values.put("prefered", phoneRequest.isPrefered()?"Y":"N");
		 values.put("createTimeStamp", new Timestamp(System.currentTimeMillis()));
		  
		 //create MapSqlParameterSource as map of input
		 SqlParameterSource parameters = new MapSqlParameterSource(values);
		
		 //execute update method
		return namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);
	}

	@Override
	public int updatePhone(PhoneRequest phoneRequest, BigInteger employeeId) {
		expirePhone(employeeId);
		return savePhone(phoneRequest,employeeId);
	}

	@Override
	public int expirePhone(BigInteger employeeId) {
		String EXPIRE_QUERY =
	            "update mobile set exp_tms = :expirationTimestamp ,prefered = :prefered where emp_id = :employeeId and exp_tms is null";
		
		Map<String, Object> values = new HashMap<String, Object>();
		 values.put("employeeId", employeeId);
		 values.put("expirationTimestamp", new Timestamp(System.currentTimeMillis()));
		 values.put("prefered","N");
		 
	    SqlParameterSource parameters = new MapSqlParameterSource(values);				
	 
	return namedParameterJdbcTemplate.update(EXPIRE_QUERY, parameters);
	}

}
