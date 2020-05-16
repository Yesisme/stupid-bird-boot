package com.epsoft.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//自定义实现springmvc拦截器
public class LoginInterceptorController implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptorController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//logger.info("请求开始{},preHandle is start");
		/*
		 * System.out.println("contentType是"+request.getContentType());
		 * if(!request.getContentType().equals("application/json")) { JSONObject json =
		 * JSON.parseObject(LoginInterceptorController.getInterceptorRequestDate(request
		 * )); System.out.println("json"+json); }else { Enumeration<String>
		 * parameterNames = request.getParameterNames();
		 * while(parameterNames.hasMoreElements()) { String parameterName =
		 * parameterNames.nextElement(); String[] parameterValues =
		 * request.getParameterValues(parameterName);
		 * System.out.println("parameterValues是"+Arrays.asList(parameterValues)); }
		 * logger.info("request{}",request.getAttributeNames()); }
		 */
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//logger.info("请求中{},postHandle is running");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//logger.info("请求结束{},postHandle is end");
	}

	private static String getInterceptorRequestDate(HttpServletRequest request) {
		try {
			int length = request.getContentLength();
			if (length < 0) {
				return null;
			}
			byte buffer[] = new byte[length];
			for (int i = 0; i < length;) {
				int readLine = request.getInputStream().read(buffer, i, length - i);
				if (readLine == -1) {
					break;
				}
				i += readLine;
				String characterEncoding = request.getCharacterEncoding();
				if (characterEncoding == null) {
					characterEncoding = "utf-8";
				}
				return new String(buffer, characterEncoding);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
