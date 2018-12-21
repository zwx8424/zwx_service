package com.zwx.framework.service;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwx.framework.context.TransContext;
import com.zwx.framework.domain.TCoreServiceTransClob;
import com.zwx.framework.domain.TCoreServiceTransRequest;
import com.zwx.framework.repository.TClobRepository;
import com.zwx.framework.repository.TCoreServiceTransRequestRepository;

@Service
public class MonitorService {
	@Autowired
	TClobRepository tClobRepository;
	@Autowired
	TCoreServiceTransRequestRepository tCoreServiceTransRequestRepository;

	@Transactional
	public void saveTrans(TransContext context) {
		if (context == null) {
			return;
		}
		context.getReqMessageId();
		Long reqMessageId = null;
		Long resMessageId = null;
		Long errorId = null;
		TCoreServiceTransClob c;
		if (StringUtils.isNotBlank(context.getRequestStr())) {
			c = new TCoreServiceTransClob();
			c.setContent(context.getRequestStr());
			c.setCreateDate(Calendar.getInstance().getTime());
			c = tClobRepository.save(c);
			reqMessageId = c.getClobId();
		}
		if (StringUtils.isNotBlank(context.getResponseStr())) {
			c = new TCoreServiceTransClob();
			c.setContent(context.getResponseStr());
			c.setCreateDate(Calendar.getInstance().getTime());
			c = tClobRepository.save(c);
			resMessageId = c.getClobId();
		}
		if (StringUtils.isNotBlank(context.getErrorMsg())) {
			c = new TCoreServiceTransClob();
			c.setContent(context.getErrorMsg());
			c.setCreateDate(Calendar.getInstance().getTime());
			c = tClobRepository.save(c);
			errorId = c.getClobId();
		}
		TCoreServiceTransRequest obj = new TCoreServiceTransRequest();
		obj.setAppIpPort(context.getAppIpPort());
		obj.setAppName(context.getAppName());
		obj.setClientCode(context.getClientCode());
		obj.setClientIpPort(context.getClientIpPort());
		obj.setClientReqNo(context.getClientReqNo());
		obj.setClientReqTime(context.getClientReqTime());
		obj.setElapsedTime(context.getElapsedTime());
		obj.setIsDuplicated("N");
		obj.setTransReqPath(context.getTransReqPath());
		obj.setTransProcessType(context.getTransProcessType());
		obj.setTransReqTime(context.getTransReqTime());
		obj.setTransResTime(context.getTransResTime());
		obj.setTransStatus(context.getTransStatus().code());
		obj.setTransResult(context.getTransResult().code());
		obj.setReqMessageId(reqMessageId);
		obj.setResMessageId(resMessageId);
		obj.setErrorId(errorId);
		obj.setPara_1(context.getPara_1());
		obj.setPara_2(context.getPara_2());
		obj = tCoreServiceTransRequestRepository.save(obj);
		context.setTransRequestId(String.valueOf(obj.getTransRequestId()));
	}

}
