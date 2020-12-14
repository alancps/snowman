package com.snowman.touristspot.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 5904867277110967104L;

	public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
