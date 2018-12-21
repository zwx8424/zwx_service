package com.zwx.framework.controller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zwx.framework.context.ThreadContext;
import com.zwx.framework.context.TransContext;

public abstract class SimpleTransController {
	private static final Logger logger = LoggerFactory.getLogger(SimpleTransController.class);

	public abstract void detailValidate(String request, String transResponse);

	public void genericHandlerException(Throwable e) {
		String errorMsg = ExceptionUtils.getFullStackTrace(e);
		logger.error(this.getClass().getName() + ":\n" + errorMsg);
		TransContext transContext = ThreadContext.getLocalTransContext();
		transContext.setErrorMsg(errorMsg);
	}
}
