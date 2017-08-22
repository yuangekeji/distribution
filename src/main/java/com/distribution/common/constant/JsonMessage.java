package com.distribution.common.constant;

public class JsonMessage {

	private boolean successful;
	private String errorCode;
	private String errorMessage;
	private Object data;

	public JsonMessage(boolean successful, String errorMessage,Object data) {
		super();
		this.successful = successful;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public JsonMessage(boolean successful, String errorCode, String errorMessage, Object data) {
		super();
		this.successful = successful;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public JsonMessage(){
		super();
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
