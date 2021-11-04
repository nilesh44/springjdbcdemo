package com.ace.springjdbcdemo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ace.springjdbcdemo.vo.GetNameResponse;
import com.ace.springjdbcdemo.vo.NameResponse;
//mapping resultSet to GetNameResponse Object
public class NameRowMapper implements RowMapper<GetNameResponse> {

	@Override
	public GetNameResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		NameResponse nameResponse=null;
		
		if(rs.getRow()==1) {
			
			do {
				
			 nameResponse=	NameResponse
						.builder()
						.nameId(String.valueOf(rs.getLong("name_id")))
				        .firstName(rs.getString("emp_fn"))
				        .lastName(rs.getString("emp_ln"))
				        .createTimestamp(rs.getTimestamp("crt_tms"))
				        .expireTimeStamp(rs.getTimestamp("exp_tms"))
				        .build();					
						
			}while(rs.next());
		}else if(rs.getRow()>0) {
			throw new RuntimeException(" more then one name exist for employee");
		}
		return GetNameResponse.builder().names(nameResponse).build();
		
				
	}

}
