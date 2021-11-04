package com.ace.springjdbcdemo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ace.springjdbcdemo.vo.PhoneResponse;
import com.ace.springjdbcdemo.vo.GetPhoneResponse;
import static com.ace.springjdbcdemo.common.ApplicationUtils.convertCharToBoolen;
public class PhoneRowMapper implements RowMapper<GetPhoneResponse> {

	@Override
	public GetPhoneResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		List<PhoneResponse> Phone= new ArrayList<PhoneResponse>();
		
		if(rs.getRow()>0) {
			
			do {
				
				PhoneResponse phoneResponse=	PhoneResponse
						.builder()
						.phoneId(String.valueOf(rs.getLong("mobile_id")))
				        .phoneNumber(rs.getString("mob_num"))
				        .prefered(convertCharToBoolen(rs.getString("prefered")))
				        .createTimestamp(rs.getTimestamp("crt_tms"))
				        .expireTimeStamp(rs.getTimestamp("exp_tms"))
				        .build();
				
				Phone.add(phoneResponse);
						
			}while(rs.next());
		}
		
		return GetPhoneResponse.builder().phones(Phone).build();
				
	}

}