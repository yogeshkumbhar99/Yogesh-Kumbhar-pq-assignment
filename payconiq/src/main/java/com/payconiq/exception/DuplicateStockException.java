package com.payconiq.exception;

public class DuplicateStockException extends RuntimeException {
	private static final long serialVersionUID = -4648444816763616192L;

	public DuplicateStockException(String message) {
		super(message);
	}
}
