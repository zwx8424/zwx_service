package com.zwx.framework.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zwx.framework.api.TransResultCode;
import com.zwx.framework.api.TransStatusCode;
import com.zwx.framework.context.ThreadContext;
import com.zwx.framework.context.TransContext;
import com.zwx.framework.service.MonitorService;
import com.zwx.framework.util.JsonUtils;

public class TransMonitorInterceptor extends HandlerInterceptorAdapter {

	public static final String DefaultCharsetName = "UTF-8";
	private static final Logger logger = LoggerFactory.getLogger(TransMonitorInterceptor.class);
	@Autowired
	private MonitorService monitorService;

	/*
	 * 预处理 初始化了TransContext对象，并进行了请求参数的d初步设置 后续可进行编码、安全控制等处理的扩展
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//解决浏览器跨域访问时，被浏览器的安全策略拒绝
		/*
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		//response.setHeader("Access-Control-Allow-Headers", "x-requested-with,CONTENT-TYPE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		*/
		TransContext context = ThreadContext.getLocalTransContext();
		context.setTransReqTime(Calendar.getInstance().getTime());
		// 需要获取到实际的客户端信息
		context.setClientIpPort(request.getRemoteAddr() + ":" + request.getRemotePort());
		context.setAppIpPort(request.getLocalAddr() + ":" + request.getLocalPort());
		//context.setAppName(Application.APP_NAME);
		// 所有接口提供服务是POST方式进入，所以不特定获取RequestMethod
		context.setTransReqPath(request.getRequestURI());
		context.setTransStatus(TransStatusCode.INIT);//设置默认值
		context.setTransResult(TransResultCode.INIT);//设置默认值
		context.setTransProcessType(TransContext.TRANS_PROCESS_TYPE_DEFAULT);
		// 在数据准备拦截器中读取请求报文体，不依赖spring boot帮助自动获取， 减少json<->java的转换次数
		// 后续使用请求报文体，可以直接从context中获取
		//byte[] reqBytes = IOUtils.toByteArray(request.getInputStream());
		//String requestStr = new String(reqBytes, DefaultCharsetName);
		//context.setRequestStr(requestStr);
		//logger.info("zwx:" + requestStr);
		// logger.info("getRequestURL:" + request.getRequestURL());
		// logger.info("getRequestURI:" + request.getRequestURI());
		// logger.info("getMethod:" + request.getMethod());
		// logger.info("getContentType:" + request.getContentType());
		// logger.info("getCharacterEncoding:" + request.getCharacterEncoding());
		// logger.info("getRemoteHost:" + request.getRemoteHost());
		// logger.info("getRemoteAddr:" + request.getRemoteAddr());
		// logger.info("getRemotePort:" + request.getRemotePort());
		// logger.info("getLocalName:" + request.getLocalName());
		// logger.info("getLocalPort:" + request.getLocalAddr());
		// logger.info("getLocalPort:" + request.getLocalPort());

		return true;

	}

	/*
	 * 返回处理（已经渲染了页面）后 将交易信息保存到DB
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		TransContext context = ThreadContext.getLocalTransContext();
		context.setTransResTime(Calendar.getInstance().getTime());
		context.setElapsedTime(context.getTransResTime().getTime() - context.getTransReqTime().getTime());

		// 只要没有异常，交易是设置是正常完成。
		context.setTransStatus(ex == null ? TransStatusCode.COMPLETE : TransStatusCode.FAILED);

		// 处理交易结果状态
		context.setTransResult(context.getTransResult() == null ? TransResultCode.SUCCEEDED : context.getTransResult());

		if (ex != null) {
			// 出现系统级异常，且没有被Controller捕捉到
			context.setTransResult(TransResultCode.FAILED);// fail
			String errMsg = ExceptionUtils.getFullStackTrace(ex);
			context.setErrorMsg(errMsg);
			//
			if (StringUtils.isBlank(context.getResponseStr())) {
				context.setResponseStr(errMsg);
			}
		} else if (StringUtils.isNotBlank(context.getErrorMsg())) {
			// 出现系统级异常，且被Controller捕捉了
			// 正常情况下，如果没有出现系统级错误，context.getErrorMsg()不应该使用并赋值
			context.setTransResult(TransResultCode.FAILED);// fail
			context.setErrorMsg(context.getErrorMsg());
			//
			if (StringUtils.isBlank(context.getResponseStr())) {
				context.setResponseStr(context.getErrorMsg());
			}
		}
		if (StringUtils.isBlank(context.getRequestStr())) {
			// 如果在Controller中已经设置了请求报文，则不用处理
			// 返回请求为空则统一处理，获取请求对象并转成JSON来记录，在Controller中不用处理返回报文
			if (context.getTransRequest() != null) {
				context.setRequestStr(JsonUtils.toJson(context.getTransRequest()));
			}
		}
		if (StringUtils.isBlank(context.getResponseStr())) {
			// 如果在Controller中已经设置了返回报文，则不用处理
			// 返回报文为空则统一处理，获取返回对象并转成JSON来记录，在Controller中不用处理返回报文
			if (context.getTransResponse() != null) {
				context.setResponseStr(JsonUtils.toJson(context.getTransResponse()));
			}
		}
		logger.info("TransContext:" + JsonUtils.toJson(context));
		try {
			monitorService.saveTrans(context);
		} catch (Throwable e) {
			// 因为业务已经处理完毕，所以假如保存交易失败，也不进行返回报文的重新处理，避免覆盖了真正的返回信息，
			logger.error(ExceptionUtils.getFullStackTrace(e));
		}
		/*
				if (StringUtils.isNotBlank(context.getResponseStr())) {
					// 如果没有走TransDataPrepareInterceptor，则不会初始化报文的请求、返回对象
					try {
						if (TransContext.TRANS_PROCESS_TYPE_XML.equals(context.getTransProcessType())) {
							response.setContentType("application/xml;charset=" + DefaultCharsetName);
						} else if (TransContext.TRANS_PROCESS_TYPE_JSON.equals(context.getTransProcessType())) {
							response.setContentType("application/json;charset=" + DefaultCharsetName);
						}
						IOUtils.write(context.getResponseStr().getBytes(DefaultCharsetName), response.getOutputStream());
					} finally {
						IOUtils.closeQuietly(response.getOutputStream());
					}
				}*/
		ThreadContext.remove();
	}

}
