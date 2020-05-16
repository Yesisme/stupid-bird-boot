package com.epsoft.demo.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.alibaba.fastjson.JSON;
import com.epsoft.demo.bean.entity.Medical;

import lombok.extern.slf4j.Slf4j;


/**
 * Jaxb 工具类，使用时根据情况自己将standalone="yes"去掉
 * 
 * @author iuv
 * @date 2017年3月13日 上午10:33:53
 */
@Slf4j
public class JaxbUtil {

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, boolean format) {
		String result = null;
		StringWriter writer = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			// 获得marshaller对象
			Marshaller marshaller = context.createMarshaller();
			writer = new StringWriter();
			writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
			// 格式化xml格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
			// 去掉生成xml的默认报文头
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, format);
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertToJava(String xml, Class<T> c) {
		if (xml == null || xml.equals(""))
			return null;
		T t = null;
		StringReader reader = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			reader = new StringReader(xml);
			t = (T) unmarshaller.unmarshal(reader);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (reader != null)
				reader.close();
		}
		return t;
	}
	
	public static void main(String[] args) {
		Medical me = new Medical();
		me.setCRBCODE("100114");
		me.setPTNBDM("平台内部代码");
		me.setName("张三");
		me.setIdNo("329489324");
		System.out.println(JaxbUtil.convertToXml(JSON.toJSONString(me), true));
	}
}