package com.zwx.framework.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CORE_SERVICE_TRANS_REQUEST")
public class TCoreServiceTransRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transRequestId;

	private String transReqPath;

	private String transProcessType;;

	@Column(name = "trans_req_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transReqTime;

	@Column(name = "trans_res_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transResTime;

	private Integer transStatus;

	private Integer transResult;

	private Long elapsedTime;

	private Long reqMessageId;

	private Long resMessageId;

	private String isDuplicated;

	private String clientCode;

	private String clientReqNo;

	@Column(name = "client_req_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date clientReqTime;

	private String clientIpPort;

	private String appIpPort;

	private String appName;

	private Long errorId;

	@Column(name = "para_1")
	private String para_1;

	@Column(name = "para_2")
	private String para_2;

	public Long getTransRequestId() {
		return transRequestId;
	}

	public void setTransRequestId(Long transRequestId) {
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

	public Integer getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(Integer transStatus) {
		this.transStatus = transStatus;
	}

	public Integer getTransResult() {
		return transResult;
	}

	public void setTransResult(Integer transResult) {
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

	public Long getErrorId() {
		return errorId;
	}

	public void setErrorId(Long errorId) {
		this.errorId = errorId;
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
