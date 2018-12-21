package com.zwx.framework.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * Title: System Infrastructure 
 * Description: This class is the httpclient utility class
 * 
 * @author weixin.zhu
 */
public class HttpClientUtilsBak {

	public static String post(String url, String content) throws IOException {
		return post(url, content, "application/json", "UTF-8");
	}

	public static String post(String url, String content, String contentType, String charset) throws IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader(HTTP.CONTENT_TYPE, (contentType + ";charset=" + charset));// application/json;charset=UTF-8"
			StringEntity s = new StringEntity(content.toString(), charset);// UTF-8
			httpPost.setEntity(s);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// System.out.println("----------------------------------------");
				// System.out.println(response.getStatusLine());

				// Get hold of the response entity
				HttpEntity entity = response.getEntity();

				// If the response does not enclose an entity, there is no need
				// to bother about connection release
				if (entity != null) {
					result = EntityUtils.toString(entity);
					EntityUtils.consumeQuietly(entity);
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return result;
	}
}
