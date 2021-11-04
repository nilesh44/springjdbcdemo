package com.ace.springjdbcdemo.exceptions;

public class RecordNotFound extends RuntimeException {

	private static final long serialVersionUID = 4912814359066616622L;

	public RecordNotFound() {
		super();
	}

	public RecordNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotFound(String message) {
		super(message);
	}

	public RecordNotFound(Throwable cause) {
		super(cause);
	}
}
