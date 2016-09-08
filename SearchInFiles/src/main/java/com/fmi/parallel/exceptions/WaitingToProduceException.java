package com.fmi.parallel.exceptions;

public class WaitingToProduceException extends RuntimeException{

	private static final long serialVersionUID = -3465662208026778218L;

	public WaitingToProduceException() {
		super();
	}

	public WaitingToProduceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WaitingToProduceException(String message) {
		super(message);
	}

}
