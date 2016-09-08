package com.fmi.parallel.exceptions;

public class ReadFromFileException extends RuntimeException{

	private static final long serialVersionUID = -4146575758584689667L;

	public ReadFromFileException() {
		super();
	}

	public ReadFromFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadFromFileException(String message) {
		super(message);
	}

}
