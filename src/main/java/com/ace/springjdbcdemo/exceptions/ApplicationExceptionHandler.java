package com.ace.springjdbcdemo.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<CommonErrorResponse> anyException(Exception exception,HttpServletRequest request) {
		
		log.error("internal server error",exception);
		request.setAttribute("errorMessage", exception);
		return new ResponseEntity<CommonErrorResponse>(new CommonErrorResponse("INTERNL SERVER ERROR"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = RecordNotFound.class)
	public ResponseEntity<CommonErrorResponse> recordNotFoundExceptionHandler(RecordNotFound recordNotFound,HttpServletRequest request) {
		  
		
		    request.setAttribute("errorMessage", recordNotFound);
		    log.error("Record Not found",recordNotFound);
		    
		return new ResponseEntity<CommonErrorResponse>(new CommonErrorResponse(recordNotFound.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = RecordPresent.class)
	public ResponseEntity<CommonErrorResponse> recordPresentExceptionHandler(RecordPresent recordPresent,HttpServletRequest request) {
		  
		log.error("Record Not Present",recordPresent); 
		request.setAttribute("errorMessage", recordPresent);
		return new ResponseEntity<CommonErrorResponse>(new CommonErrorResponse(recordPresent.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidRequestParameterException.class)
	public ResponseEntity<CommonErrorResponse> invalidRequestParameterExceptionHandler(InvalidRequestParameterException invalidRequestParameterException,HttpServletRequest request) {
		 
		log.error("Invalid Request Parameter Exception",invalidRequestParameterException);
		request.setAttribute("errorMessage", ExceptionUtils.getStackTrace(invalidRequestParameterException).toString());
		return new ResponseEntity<CommonErrorResponse>(new CommonErrorResponse(invalidRequestParameterException.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
