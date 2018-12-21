package com.zwx.framework.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CORE_SERVICE_TRANS_CLOB")
public class TCoreServiceTransClob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clobId;

	@Column(name = "createDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", columnDefinition = "CLOB", nullable = true)
	private String content;

	public Long getClobId() {
		return clobId;
	}

	public void setClobId(Long clobId) {
		this.clobId = clobId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
