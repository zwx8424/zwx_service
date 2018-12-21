package com.zwx.framework.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransHeader extends SimpleTransObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 922825749629116079L;
	public static final String BEAN_NAME = "transHeader";

	/**
	 * Request id from client
	 */
	private String requestId;
	/**
	 * Request time from client, format: yyyy-MM-dd hh:mm:ss
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date requestTime;
	/**
	 * Transaction code
	 */
	private String transCode;
	/**
	 * Transaction type
	 */
	private Integer transType;
	/**
	 * Code of client
	 */
	private String clientCode;
	/**
	 * Result of Trans
	 */
	private Integer transResult;
	/**
	 * errMsg of Trans
	 */
	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public Integer getTransResult() {
		return transResult;
	}

	public void setTransResult(Integer transResult) {
		this.transResult = transResult;
	}

}
