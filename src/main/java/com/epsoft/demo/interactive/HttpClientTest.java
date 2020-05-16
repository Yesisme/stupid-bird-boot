package com.epsoft.demo.interactive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	/**
	 * post请求传输json数据
	 * 
	 * @param url
	 * @param json
	 * @param encoding
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendPostDataByJson(String url, String json) throws ClientProtocolException, IOException {
		String result = "";

		// 创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 设置参数到请求对象中
		StringEntity se = new StringEntity(json, ContentType.APPLICATION_JSON);
		httpPost.setEntity(se);

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = httpClient.execute(httpPost);

		// 获取结果实体
		// 判断网络连接状态码是否正常(0--200都数正常)
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		// 释放链接
		response.close();
		return result;
	}

	public static String sendPostDataByMap(String url, Map<String, String> map, String encoding) throws ClientProtocolException, IOException {
		String result = "";

		// 创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 装填参数
		List<NameValuePair> list = new ArrayList<>();
		if (map != null) {
			Set<Entry<String, String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(list, encoding));

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		
		httpPost.setHeader("Content-type","application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		//Header[] array = ListCovertHeaderArray();
		//httpPost.setHeaders(array);
		System.out.println("httpPost是"+httpPost);
		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = httpClient.execute(httpPost);
		// 获取结果实体
		// 判断网络连接状态码是否正常(0--200都数正常)
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		// 释放链接
		response.close();
		return result;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private static Header[] ListCovertHeaderArray() {
		List<Map> headerList = new ArrayList<>();
		Map<Object, Object> headerMap = new HashMap<>();
		headerMap.put("Content-type", "application/x-www-form-urlencoded");
		headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		Header[] strings = new Header[headerList.size()];
		Header[] array = headerList.toArray(strings);
		return array;
	}
}
