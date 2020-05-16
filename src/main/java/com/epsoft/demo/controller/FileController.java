package com.epsoft.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epsoft.demo.service.file.IFileService;

@RestController
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/*@Autowired
	private FTPUtil ftpUtil;*/
	
	@Autowired
	private IFileService iFileService;

	@PostMapping("upload")
	public Map<String, Object> upload(MultipartFile file,HttpServletRequest request,HttpServletRequest response) throws IOException{
		Map<String,Object> resultMap = new HashMap<>();
		String path = request.getSession().getServletContext().getRealPath("upload");
		logger.info("文件放置路径为:{}",path);
		String targetFileName =  iFileService.upload(file, path);
		if(StringUtils.isBlank(targetFileName)){
			 resultMap.put("success",false);
             resultMap.put("msg","上传失败");
             return resultMap;		
		}
		 String url = "ftp://192.168.43.251/"+targetFileName;
         resultMap.put("success",true);
         resultMap.put("msg","上传成功");
         resultMap.put("file_path",url);
         return resultMap;
	}

	@PostMapping("threeLine")
	public Map<String,Object> threeLine(String areaCode){
		
		return null;
	}
}
