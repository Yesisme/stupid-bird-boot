package com.epsoft.demo.service.file.impl;

import java.io.File;
import java.io.IOException;

import org.jgroups.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epsoft.demo.service.file.IFileService;
import com.epsoft.demo.utils.FTPUtil;
import com.google.common.collect.Lists;

@Service
public class IFileServiceImpl implements IFileService{

	private static final Logger logger = LoggerFactory.getLogger(IFileServiceImpl.class);
	
	@Autowired
	private FTPUtil ftpUtil;
	
	@Override
	public String upload(MultipartFile file, String path) {
		if(file.isEmpty()){
			return "文件为空";
		}
		String fileName = file.getOriginalFilename();//获取文件名
		logger.info("上传的文件名为:"+fileName);
		
		String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件的后缀名
		logger.info("文件的后缀名为"+suffixName);
		String uploadFileName = UUID.randomUUID().toString()+"."+suffixName;//网页上的新文件名
		logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);
		
		File fileDir = new File(path);// 设置文件存储路径
		if(!fileDir.exists()){        // 检测是否存在目录
			fileDir.setWritable(true);
			fileDir.mkdirs();
		}
		
		File targerFile = new File(path,uploadFileName);
		 
		try {
			 
			file.transferTo(targerFile);//文件上传成功
			
			FTPUtil.uploadFile(Lists.newArrayList(targerFile)); //已经上传到ftp服务器上
		
			targerFile.delete();//删除文件夹和图片
		} catch (IOException e) {
			logger.info("文件上传失败"+e);
		}
		
		return targerFile.getName();

	}

	@Override
	public String download() {
		return null;
	}

}
