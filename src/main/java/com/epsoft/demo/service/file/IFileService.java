package com.epsoft.demo.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

	public String upload(MultipartFile file,String path);

	String download();
}
