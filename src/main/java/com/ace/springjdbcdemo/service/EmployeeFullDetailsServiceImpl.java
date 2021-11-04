package com.ace.springjdbcdemo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.springjdbcdemo.vo.GetEmailResponse;
import com.ace.springjdbcdemo.vo.GetEmployeeFullDetailsResponse;
import com.ace.springjdbcdemo.vo.GetNameResponse;
import com.ace.springjdbcdemo.vo.GetPhoneResponse;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class EmployeeFullDetailsServiceImpl implements EmployeeFullDetailsService{

	@Autowired
	NameService nameService;
	
	@Autowired
	PhoneService phoneService;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public GetEmployeeFullDetailsResponse getEmployeeFullDetails(boolean getExpired, String employeeId) {
		
		
		CompletableFuture<GetNameResponse> nameFuture=CompletableFuture
				.supplyAsync(() ->
		                        nameService.findByEmpId(employeeId, getExpired))
				.handle((name,ex)->{
			if(ex!=null) {
				log.error("exception while retriving name",ex);
				return null;
			}
			return name;
		});
		
		CompletableFuture<GetPhoneResponse> phoneFuture=CompletableFuture
				.supplyAsync(() ->
		                        phoneService.findPhoneByEmpId(employeeId, getExpired))
				.handle((phone,ex)->{
			if(ex!=null) {
				log.error("exception while retriving phone",ex);
				return null;
			}
			return phone;
		});
		
		CompletableFuture<GetEmailResponse> emailFuture=CompletableFuture
				.supplyAsync(() ->
		                        emailService.findEmailByEmpId(employeeId, getExpired))
				.handle((email,ex)->{
			if(ex!=null) {
				log.error("exception while retriving email",ex);
				return null;
			}
			return email;
		});
		
		try {
			CompletableFuture.allOf(nameFuture,phoneFuture,emailFuture);
			
			return GetEmployeeFullDetailsResponse.builder()
					.employeeId(employeeId)
					.names(nameFuture.get()!=null?nameFuture.get().getNames():null)
					.phones(phoneFuture.get()!=null?phoneFuture.get().getPhones():null)
					.emails(emailFuture.get()!=null?emailFuture.get().getEmails():null)
					.build();
			
		
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} catch (ExecutionException e) {
		
			e.printStackTrace();
		}
		
		return null;
	}

}
