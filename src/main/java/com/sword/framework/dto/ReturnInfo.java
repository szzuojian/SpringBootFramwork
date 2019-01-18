package com.sword.framework.dto;

import java.io.Serializable;

public class ReturnInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7130013740782861082L;

	private String success;

	private String message;

	private String data;

	private String exception;

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
