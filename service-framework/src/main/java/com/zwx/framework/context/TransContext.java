package com.zwx.framework.context;

import java.util.Date;

import com.zwx.framework.api.TransRequest;
import com.zwx.framework.api.TransResponse;
import com.zwx.framework.api.TransResultCode;
import com.zwx.framework.api.TransStatusCode;

public class TransContext {
	public static final String TRANS_PROCESS_TYPE_JSON = "JSON";
	public static final String TRANS_PROCESS_TYPE_XML = "XML";
	public static final String TRANS_PROCESS_TYPE_DEFAULT = "DEFAULT";

	private TransRequest transRequest;
	private TransResponse transResponse;
	
	private String transRequestId;//内部交易记录的ID
	private String transReqPath;
	private String transProcessType;// 流程类型，JSON-正常流程，基于JSON规范，XML-非正常流程，基于XML规范
	private Date transReqTime;
	private Date transResTime;
	private TransStatusCode transStatus;
	private TransResultCode transResult;
	private Long elapsedTime;
	private Long reqMessageId;
	private Long resMessageId;
	private String isDuplicated;
	private String clientCode;
	private String clientReqNo;
	private Date clientReqTime;
	private String clientIpPort;
	private String appIpPort;
	private String appName;
	private String requestStr;
	private String responseStr;
	private Long errorId;
	private String errorMsg;
	private String para_1;
	private String para_2;

	public TransRequest getTransRequest() {
		return transRequest;
	}

	public void setTransRequest(TransRequest transRequest) {
		this.transRequest = transRequest;
	}

	public TransResponse getTransResponse() {
		return transResponse;
	}

	public void setTransResponse(TransResponse transResponse) {
		this.transResponse = transResponse;
	}

	public String getTransRequestId() {
		return transRequestId;
	}

	public void setTransRequestId(String transRequestId) {
		this.transRequestId = transRequestId;
	}

	public String getTransReqPath() {
		return transReqPath;
	}

	public void setTransReqPath(String transReqPath) {
		this.transReqPath = transReqPath;
	}

	public String getTransProcessType() {
		return transProcessType;
	}

	public void setTransProcessType(String transProcessType) {
		this.transProcessType = transProcessType;
	}

	public Date getTransReqTime() {
		return transReqTime;
	}

	public void setTransReqTime(Date transReqTime) {
		this.transReqTime = transReqTime;
	}

	public Date getTransResTime() {
		return transResTime;
	}

	public void setTransResTime(Date transResTime) {
		this.transResTime = transResTime;
	}

	public TransStatusCode getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(TransStatusCode transStatus) {
		this.transStatus = transStatus;
	}

	public TransResultCode getTransResult() {
		return transResult;
	}

	public void setTransResult(TransResultCode transResult) {
		this.transResult = transResult;
	}

	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public Long getReqMessageId() {
		return reqMessageId;
	}

	public void setReqMessageId(Long reqMessageId) {
		this.reqMessageId = reqMessageId;
	}

	public Long getResMessageId() {
		return resMessageId;
	}

	public void setResMessageId(Long resMessageId) {
		this.resMessageId = resMessageId;
	}

	public String getIsDuplicated() {
		return isDuplicated;
	}

	public void setIsDuplicated(String isDuplicated) {
		this.isDuplicated = isDuplicated;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientReqNo() {
		return clientReqNo;
	}

	public void setClientReqNo(String clientReqNo) {
		this.clientReqNo = clientReqNo;
	}

	public Date getClientReqTime() {
		return clientReqTime;
	}

	public void setClientReqTime(Date clientReqTime) {
		this.clientReqTime = clientReqTime;
	}

	public String getClientIpPort() {
		return clientIpPort;
	}

	public void setClientIpPort(String clientIpPort) {
		this.clientIpPort = clientIpPort;
	}

	public String getAppIpPort() {
		return appIpPort;
	}

	public void setAppIpPort(String appIpPort) {
		this.appIpPort = appIpPort;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRequestStr() {
		return requestStr;
	}

	public void setRequestStr(String requestStr) {
		this.requestStr = requestStr;
	}

	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}

	public Long getErrorId() {
		return errorId;
	}

	public void setErrorId(Long errorId) {
		this.errorId = errorId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getPara_1() {
		return para_1;
	}

	public void setPara_1(String para_1) {
		this.para_1 = para_1;
	}

	public String getPara_2() {
		return para_2;
	}

	public void setPara_2(String para_2) {
		this.para_2 = para_2;
	}
}
