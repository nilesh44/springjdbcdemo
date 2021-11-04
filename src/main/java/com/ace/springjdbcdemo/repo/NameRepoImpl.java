package com.ace.springjdbcdemo.repo;

import static com.ace.springjdbcdemo.utils.Constant.FIND_BY_NAME_ID;
import static com.ace.springjdbcdemo.utils.Constant.FIND_BY_NAME_ID_ALL_EXPIRED;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ace.springjdbcdemo.exceptions.RecordNotFound;
import com.ace.springjdbcdemo.mapper.NameRowMapper;
import com.ace.springjdbcdemo.vo.Employee;
import com.ace.springjdbcdemo.vo.GetNameResponse;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class NameRepoImpl implements NameRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public GetNameResponse findByEmpId(BigInteger employeeId,boolean getExpired) {
		 
		String sql = FIND_BY_NAME_ID;
		
		if(getExpired) {
			 sql = FIND_BY_NAME_ID_ALL_EXPIRED;
		}
		try {
			GetNameResponse response=namedParameterJdbcTemplate
					.queryForObject(
							sql,  
							new MapSqlParameterSource("employeeId", employeeId),
							new NameRowMapper());
			response.setEmployeeId(employeeId);
			return response;
			
		}catch(EmptyResultDataAccessException emptyResultDataAccessException) {
			log.error("employee not found",emptyResultDataAccessException);
			return null;
		}
		
	}

	@Override
	public int saveEmployeeName(Employee employee,BigInteger employeeId) {
		
		
		//create nameParameter query
		 String INSERT_QUERY =
		            "insert into name(emp_id,emp_fn,emp_ln,crt_tms) values( :employeeId , :firstname , :lastname , :createTimeStamp)";
		
		 //crerate map of column and value
		 Map<String, Object> values = new HashMap<String, Object>();
		 values.put("employeeId", employeeId);
		 values.put("firstname", employee.getFirstName());
		 values.put("lastname", employee.getLastName());
		 values.put("createTimeStamp", new Timestamp(System.currentTimeMillis()));
		  
		 //create MapSqlParameterSource as map of input
		 SqlParameterSource parameters = new MapSqlParameterSource(values);
		
		 //execute update method
		return namedParameterJdbcTemplate.update(INSERT_QUERY, parameters);
		
	}

	@Override
	public int updateEmployeeName(Employee employee,BigInteger employeeId) {
		//exprie old create new row
		expireEmployee(employeeId);
		return saveEmployeeName(employee, employeeId);
		
		
	}
	
	@Override
	public int expireEmployee(BigInteger employeeId) {
		
		String EXPIRE_QUERY =
	            "update name set exp_tms = :expirationTimestamp where emp_id = :employeeId and exp_tms is null";
		
		Map<String, Object> values = new HashMap<String, Object>();
		 values.put("employeeId", employeeId);
		 values.put("expirationTimestamp", new Timestamp(System.currentTimeMillis()));
		 
	    SqlParameterSource parameters = new MapSqlParameterSource(values);				
	 
	return namedParameterJdbcTemplate.update(EXPIRE_QUERY, parameters);
		
	}
	
}
