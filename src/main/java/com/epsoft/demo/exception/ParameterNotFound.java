package com.epsoft.demo.exception;

public class ParameterNotFound extends RuntimeException {

	public ParameterNotFound() {
		super();
	}

	public ParameterNotFound(String message) {
		super(message);
	}

	public ParameterNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public ParameterNotFound(Throwable cause) {
		super(cause);
	}
}
