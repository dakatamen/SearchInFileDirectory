package com.fmi.parallel.exceptions;

public class ThreadJoinException extends RuntimeException{

	private static final long serialVersionUID = 6205504103116120717L;

	public ThreadJoinException() {
		super();
	}

	public ThreadJoinException(String message, Throwable cause) {
		super(message, cause);
	}

	public ThreadJoinException(String message) {
		super(message);
	}

}
