package com.zwx.framework.api;

public class TransResponse extends SimpleTransObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358742157929586005L;
	public static final String BEAN_NAME = "transResponse";
	private TransHeader transHeader;
	private String transResponseBody;

	public TransHeader getTransHeader() {
		return transHeader;
	}

	public void setTransHeader(TransHeader transHeader) {
		this.transHeader = transHeader;
	}

	public String getTransResponseBody() {
		return transResponseBody;
	}

	public void setTransResponseBody(String transResponseBody) {
		this.transResponseBody = transResponseBody;
	}
}
