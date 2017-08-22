package com.distribution.common.exception;

/**
 * Business Exception.
 * 
 * This exception will triggers rollback actions for all transactions.
 * 
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public BizException(String errorCode) {
		super(errorCode);
	}

	public BizException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
