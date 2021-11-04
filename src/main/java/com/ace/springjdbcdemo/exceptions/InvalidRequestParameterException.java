package com.ace.springjdbcdemo.exceptions;

public class InvalidRequestParameterException extends RuntimeException {

	private static final long serialVersionUID = 4912814359066616622L;

	public InvalidRequestParameterException() {
		super();
	}

	public InvalidRequestParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestParameterException(String message) {
		super(message);
	}

	public InvalidRequestParameterException(Throwable cause) {
		super(cause);
	}
}
