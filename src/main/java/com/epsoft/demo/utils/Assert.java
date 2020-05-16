package com.epsoft.demo.utils;


import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.epsoft.demo.exception.ParameterNotFound;

public class Assert{

	public static void  paramsNotNull(JSONObject json,String... keys) {
		for (String key : keys) {
			Object o = json.get(key);
			if(o==null) {
				throw new ParameterNotFound(key+"不能为空");
			}else if(StringUtils.isEmpty((String)o)) {
				throw new ParameterNotFound(key+"不能为空");
			}
			if(StringUtils.equals((String)o, "null")) {
				throw new ParameterNotFound(key+"不能为空");
			}
		}		
	}
	
}
