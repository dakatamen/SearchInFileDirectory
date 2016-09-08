package com.fmi.parallel.exceptions;

public class WaitingToConsumeException extends RuntimeException {

	private static final long serialVersionUID = -588980405200537935L;

	public WaitingToConsumeException() {
		super();
	}

	public WaitingToConsumeException(String message, Throwable cause) {
		super(message, cause);
	}

	public WaitingToConsumeException(String message) {
		super(message);
	}

}
