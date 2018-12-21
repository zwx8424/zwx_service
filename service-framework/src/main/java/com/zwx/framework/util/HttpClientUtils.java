package com.zwx.framework.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient4.3工具类
 * 
 * @author tech
 * @date 2016-03-29
 *
 */
public class HttpClientUtils {
	public static final String CONTENT_TYPE__JSON = "application/json";
	public static final String CONTENT_TYPE__FORM = "application/x-www-form-urlencoded";
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录
	private static RequestConfig requestConfig = null;

	static {
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
	}

	/**
	 * post请求传输String参数
	 * Content-type:application/json
	 * @param url
	 *            url地址
	 * @param strParam
	 *            参数
	 * @return
	 */
	public static String httpPost(String url, String strParam) {
		// post请求返回结果
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (null != strParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType(CONTENT_TYPE__JSON);
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(response.getEntity(), "utf-8");
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static String httpGet(String url) {
		// get请求返回结果
		String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		// 设置请求和传输超时时间
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "utf-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return result;
	}
}