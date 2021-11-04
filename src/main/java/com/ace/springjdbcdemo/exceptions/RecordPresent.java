package com.ace.springjdbcdemo.exceptions;

public class RecordPresent extends RuntimeException {

	private static final long serialVersionUID = 4912814359066616622L;

	public RecordPresent() {
		super();
	}

	public RecordPresent(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordPresent(String message) {
		super(message);
	}

	public RecordPresent(Throwable cause) {
		super(cause);
	}
}
