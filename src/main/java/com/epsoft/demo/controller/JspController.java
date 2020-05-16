package com.epsoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.epsoft.demo.bean.dto.SiCardCheckInfoDto;


@Controller
public class JspController {

	@GetMapping("jsp/upload")
	public String upload(ModelMap modelMap){
		
		return "upload";
	}
	
	@GetMapping("jsp/threeLink")
	public String ThreeLevelLink(ModelMap modelMap) {
		
		return "threeLink"; 
	}
	
	@GetMapping("jsp/websocket")
	public String websocket(ModelMap modelMap) {
		
		return "websocket"; 
	}
	
	@GetMapping("jsp/sicardInfoCheck")
	public String sicardInfoCheck(ModelMap modelMap) {
		
		return "sicardInfoCheck";
	}
	
	@PostMapping("jsp/sicardInfoCheck.do")
	@ResponseBody
	public String getSiCardInfo(@RequestBody SiCardCheckInfoDto siCardCheckInfoDto) {
		JSONObject json = new JSONObject();
		json.put("accessKey","3298472398473928");
		json.put("checkResult", "1");
		json.put("time","20191111");
		json.put("busiType", "201");
		return json.toString();
	}
		
	
	
}
