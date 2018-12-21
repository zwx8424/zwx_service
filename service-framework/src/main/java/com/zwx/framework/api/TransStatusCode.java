package com.zwx.framework.api;

public enum TransStatusCode {
	COMPLETE(1), FAILED(0), INIT(-1), PROCESSING(2);
	private Integer code;

	TransStatusCode(Integer code) {
		this.code = code;
	}

	public Integer code() {
		return this.code;
	}

	public boolean isCompleted() {
		return this.equals(COMPLETE);
	}

	public boolean isFailed() {
		return this.equals(FAILED);
	}
}
