package com.payconiq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class CommonStockException extends RuntimeException {

	private static final long serialVersionUID = 3650982362437524281L;

	public CommonStockException(String message) {
		super(message);
	}
}
