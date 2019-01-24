package com.sword.framework.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReturnInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7130013740782861082L;

	private String success;

	private String message;

	private Map data = new HashMap();

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

	public Map getData() {
		return data;
	}

	public void setData(String key, String data) {
		this.data.put(key, data);
	}

}
