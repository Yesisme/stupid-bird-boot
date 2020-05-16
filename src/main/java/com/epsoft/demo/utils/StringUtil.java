package com.epsoft.demo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;

import com.alibaba.fastjson.JSON;

public class StringUtil {

	public static boolean testJSONObjectOrJSONArray(com.alibaba.fastjson.JSONObject jsonType,String assertStr) throws JSONException {
		boolean flag = true;
 
		Object obj = new JSONTokener(JSON.toJSONString(jsonType.get(assertStr))).nextValue();
		if(obj instanceof JSONObject) {
		
			flag = false;
		}else if(obj instanceof JSONArray){
			//System.out.println("=======================JSONArray格式:"+listArry);
			return flag;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		String jsonArrayType = "<body><state>1</state><result></result><PBXX><ZLF>0</ZLF><YYXE>0</YYXE><YSMC>陈铁忠</YSMC><GHF>14</GHF><GZRQ>2018-12-17</GZRQ><YSDM>9114</YSDM><PBID>2018121710191141</PBID><KSMC>疼痛科</KSMC><KSDM>101</KSDM><TINGZHENBZ>0</TINGZHENBZ><ZBLB>1</ZBLB><GHLB>2</GHLB><HYSL>30</HYSL><SYHY>30</SYHY><YYRS>0</YYRS></PBXX></body>";
		//String jsonArrayType = "<body><state>1</state><result></result><PBXX><ZLF>0</ZLF><YYXE>0</YYXE><YSMC>陈铁忠</YSMC><GHF>14</GHF><GZRQ>2018-12-17</GZRQ><YSDM>9114</YSDM><PBID>2018121710191141</PBID><KSMC>疼痛科</KSMC><KSDM>101</KSDM><TINGZHENBZ>0</TINGZHENBZ><ZBLB>1</ZBLB><GHLB>2</GHLB><HYSL>30</HYSL><SYHY>30</SYHY><YYRS>0</YYRS></PBXX><PBXX><ZLF>0</ZLF><YYXE>0</YYXE><YSMC>陈铁忠</YSMC><GHF>14</GHF><GZRQ>2018-12-17</GZRQ><YSDM>9114</YSDM><PBID>2018121710191141</PBID><KSMC>疼痛科</KSMC><KSDM>101</KSDM><TINGZHENBZ>0</TINGZHENBZ><ZBLB>1</ZBLB><GHLB>2</GHLB><HYSL>30</HYSL><SYHY>30</SYHY><YYRS>0</YYRS></PBXX></body>";
		String jsonObject = XML.toJSONObject(jsonArrayType).toString();
		com.alibaba.fastjson.JSONObject jsonType = JSON.parseObject(JSON.parseObject(jsonObject).get("body").toString());
		System.out.println(StringUtil.testJSONObjectOrJSONArray(jsonType,"PBXX"));
	}
		
}
