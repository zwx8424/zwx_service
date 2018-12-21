package com.zwx.framework.util;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Title: System Infrastructure
 * Description: This class is the json utility class
 * 
 * @author weixin.zhu
 */
public class JsonUtils {
	private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private final static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setTimeZone(TimeZone.getDefault());
	}

	public static String toJson(Object input) {
		String result = null;
		if (input != null) {
			try {
				result = objectMapper.writeValueAsString(input);
			} catch (JsonProcessingException e) {
				logger.error("to Json error for :\n {}", new Object[] { input });
				throw ExceptionUtils.parse(e);
			}
		}
		return result;
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		T result = null;
		if (!StringUtils.isNullOrEmpty(json)) {
			try {
				result = objectMapper.readValue(json, clazz);
			} catch (Exception e) {
				logger.error("to Object error for :\n {}", json);
				throw ExceptionUtils.parse(e);
			}
		}
		return result;
	}
}