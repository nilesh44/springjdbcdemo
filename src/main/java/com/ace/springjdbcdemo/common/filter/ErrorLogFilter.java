package com.ace.springjdbcdemo.common.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.ace.springjdbcdemo.vo.ErrorAudit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ErrorLogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper( httpResponse);
		ContentCachingRequestWrapper wrapperReuest = new ContentCachingRequestWrapper(httpRequest);

		//above chain.filter we are performing operation before sending request to controller
		
		chain.doFilter(wrapperReuest, httpResponse);

		// below chail.filter all operation are perform before sending response
		if (!StringUtils.startsWith(String.valueOf(httpResponse.getStatus()), "2")) {

			String payload = new String(wrapperReuest.getContentAsByteArray(), "utf-8");

			Object errorMessge=wrapperReuest.getAttribute("errorMessage");
			
			ErrorAudit errorAudit = ErrorAudit
					.builder()
					.uri(getUrl(wrapperReuest))
					.methodType(wrapperReuest.getMethod().toString())
					.timestamp(LocalDateTime.now().toString())
					
					.body(null!=payload?
							new Gson().fromJson(payload, JsonElement.class):
								null)
					
					.errorMessge(null!=errorMessge?
							StringUtils.normalizeSpace(errorMessge.toString()):
								null)
					.build();
			
			try {
				Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
				String errorAuditString = gsonPretty.toJson(errorAudit);
			
				//insted of printing errorAudit in logs we can insert into no SQL db through kafka message queue 
				//and we have one Ui for analysis of that error.
				log.warn("Error occur for request {} ", errorAuditString);

			} catch (Exception e) {
				log.error("error occur while parsing gson ", e);
			}
		}
	}
	
	
	private String getUrl(ContentCachingRequestWrapper request) {
		return   request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() +       // "8080"
	             request.getRequestURI() +       // "/people"
	             (StringUtils.isNotBlank( request.getQueryString()) 
	            		 ? ("?" + request.getQueryString())
	            				 :"");                         // "?"// "lastname=Fox&age=30"
	}

}
