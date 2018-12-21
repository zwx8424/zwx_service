package com.zwx.framework.api;


public class TransRequest extends SimpleTransObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7203891414150052032L;
	public static final String BEAN_NAME = "transRequest";
	private TransHeader transHeader;
	private String transRequestBody;

	public TransHeader getTransHeader() {
		return transHeader;
	}

	public void setTransHeader(TransHeader transHeader) {
		this.transHeader = transHeader;
	}

	public String getTransRequestBody() {
		return transRequestBody;
	}

	public void setTransRequestBody(String transRequestBody) {
		this.transRequestBody = transRequestBody;
	}
}
