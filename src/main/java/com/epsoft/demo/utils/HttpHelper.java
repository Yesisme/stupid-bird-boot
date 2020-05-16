package com.epsoft.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.epsoft.demo.exception.ParamterVaildException;

public class HttpHelper {

	private static Logger log = LoggerFactory.getLogger(HttpHelper.class);
	private static String header = "[{\"key\":\"Content-Type\",\"value\":\"application/json;charset=UTF-8\"}]";
	private static String contentType = "application/json";
	private static String charset = "utf-8";

	public static String httpPost(String v_url, String str, int cycles) {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost postreq = new HttpPost(v_url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(10000).build();
		postreq.setConfig(requestConfig);
		JSONArray headerArray = JSONArray.parseArray(header);
		for (int i = 0; i < headerArray.size(); i++) {
			postreq.addHeader(headerArray.getJSONObject(i).getString("key"),
					headerArray.getJSONObject(i).getString("value"));
		}
		StringEntity requestEntity = null;
		String resultStr = null;
		try {
			requestEntity = new StringEntity(str, ContentType.APPLICATION_JSON);
			postreq.setEntity(requestEntity);
			int i = 0;
			while (i < cycles) {
				response = client.execute(postreq, new BasicHttpContext());
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					resultStr = EntityUtils.toString(entity, "utf-8");
				}
				if (response.getStatusLine().getStatusCode() != 200) {
					log.error(resultStr);
					String errMessage = "请求url=" + v_url + ",请求失败,http返回code:"
							+ response.getStatusLine().getStatusCode();
					log.error(errMessage);
					i++;
					if (i == cycles) {
						throw new Exception(errMessage);
					}
				} else {
					break;
				}
			}
			return resultStr;
		} catch (Exception e) {
			String errMessage = "请求url=" + v_url + ", 返回异常编号=" + e.getMessage();
			log.info(errMessage);
			throw new ParamterVaildException(errMessage + "," + e.getMessage());
		} finally {
			try {
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			if (response != null)
				try {
					response.close();
				} catch (Exception e) {
					log.error(e.getMessage());
				}	
		}
	}

	public static String httpPost(String v_url, String str, String header, String contentType, String charset,
			int cycles) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost postreq = new HttpPost(v_url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();
		postreq.setConfig(requestConfig);
		JSONArray headerArray = JSONArray.parseArray(header);
		for (int i = 0; i < headerArray.size(); i++) {
			postreq.addHeader(headerArray.getJSONObject(i).getString("key"),
					headerArray.getJSONObject(i).getString("value"));
		}
		StringEntity requestEntity = null;
		String resultStr = null;

		try {
			requestEntity = new StringEntity(str, ContentType.APPLICATION_JSON);
			postreq.setEntity(requestEntity);
			int i = 0;
			while (i < cycles) {
				response = client.execute(postreq, new BasicHttpContext());
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					resultStr = EntityUtils.toString(entity, "utf-8");
				}
				if (response.getStatusLine().getStatusCode() != 200) {
					log.error(resultStr);
					String errMessage = "请求url=" + v_url + ",请求失败,http返回code:"
							+ response.getStatusLine().getStatusCode();
					log.error(errMessage);
					i++;
					if (i == cycles) {
						throw new ParamterVaildException(errMessage);
					}
				} else {
					break;
				}
			}
			return resultStr;
		} catch (Exception e) {
			String errMessage = "请求url=" + v_url + ", 返回异常编号=" + e.getMessage();
			log.info(errMessage);
			throw new ParamterVaildException(errMessage + "," + e.getMessage());
		} finally {
			try {
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			if (response != null)
				response.close();
		}
	}

	public static void main(String[] args) {
		httpPost("", "sss", 5);
	}
}