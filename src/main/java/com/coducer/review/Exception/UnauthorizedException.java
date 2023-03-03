package com.coducer.review.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1918959694720387593L;

	public UnauthorizedException(String message) {
		super(message);
	}
}