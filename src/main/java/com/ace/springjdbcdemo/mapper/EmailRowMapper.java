package com.ace.springjdbcdemo.mapper;

import static com.ace.springjdbcdemo.common.ApplicationUtils.convertCharToBoolen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ace.springjdbcdemo.vo.EmailResponse;
import com.ace.springjdbcdemo.vo.GetEmailResponse;

public class EmailRowMapper implements RowMapper<GetEmailResponse> {

	@Override
	public GetEmailResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		List<EmailResponse> email= new ArrayList<EmailResponse>();
		
		if(rs.getRow()>0) {
			
			do {
				
				EmailResponse emailResponse = EmailResponse
						.builder()
						.emailId(String.valueOf(rs.getLong("email_id")))
				        .emailAddress(rs.getString("email_add"))
				        .prefered(convertCharToBoolen(rs.getString("prefered")))
				        .createTimestamp(rs.getTimestamp("crt_tms"))
				        .expireTimeStamp(rs.getTimestamp("exp_tms"))
				        .build();
				
				email.add(emailResponse);
						
			}while(rs.next());
		}
		
		return GetEmailResponse.builder().emails(email).build();
				
	}

}