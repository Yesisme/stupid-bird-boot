package com.epsoft.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.alibaba.fastjson.JSON;

public class XmlUtils {

	public static String xml2jsonString(String si) throws IOException, JSONException  {   
		 	//InputStream in = XmlUtils.class.getResourceAsStream("/student.xml");
		    InputStream in = new FileInputStream(si);
	        String xml = IOUtils.toString(in);
	        JSONObject xmlJSONObj = XML.toJSONObject(xml);
	        return xmlJSONObj.toString();
	
    } 
  public static void main(String[] args) throws IOException, JSONException {
	  String si = "<response>\n" +
			"   <body>\n" + 
	  		"   <sex>man</sex>\n" + 
	  		"    <age>18</age>\n" +
	  		"   </body>\n" + 
	  		"	</response>";
	  JSONObject xmlJSONObj = XML.toJSONObject(si);
	   Map map = (Map) JSON.parse(xmlJSONObj.toString());
	    System.out.println(((Map)map.get("response")).get("body"));
      

}

}
