package com.zwx.framework.context;

import java.util.HashMap;

public class ThreadContext {
	private static ThreadLocal<HashMap<String, Object>> threadContext = new ThreadLocal<HashMap<String, Object>>();
	private static ThreadLocal<TransContext> transContext = new ThreadLocal<TransContext>();
	public static final String FLAG_TRANS_CONTEXT = "ThreadContext_TransContext";

	public static HashMap<String, Object> getLocalThreadContext() {
		HashMap<String, Object> localThreadContextMap = threadContext.get();
		if (localThreadContextMap == null) {
			localThreadContextMap = new HashMap<String, Object>();
			threadContext.set(localThreadContextMap);
		}
		return localThreadContextMap;
	}

	public static TransContext getLocalTransContext() {
		TransContext localTransContext = transContext.get();
		if (localTransContext == null) {
			localTransContext = new TransContext();
			transContext.set(localTransContext);
		}
		return localTransContext;
	}

	public static void remove() {
		transContext.remove();
		transContext.remove();
	}
}
