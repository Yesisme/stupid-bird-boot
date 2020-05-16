package com.epsoft.demo.base64;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Base64PictureUtil {

	public static String base642String(String filePath) throws Exception {
		File file = new File(filePath);
		if(!file.exists()) {
			throw new FileNotFoundException(filePath +"can not find");
		}
		BufferedInputStream bi = null;
		byte[] data =null;
		try {
			bi = new BufferedInputStream(new FileInputStream(file));
			data = new byte[bi.available()];
			bi.read(data);
			String encodeToString = Base64.getEncoder().encodeToString(data);
			return encodeToString;
		}catch(Exception e) {
			 e.printStackTrace();
	         throw  new RuntimeException(e);
		}finally {
			IOUtils.closeQuietly(bi);
		}
	}
	
	public static void saveFile(String base642String,String savePath) throws IOException {
		byte[] decodeData = Base64.getDecoder().decode(base642String);
		FileUtils.writeByteArrayToFile(new File(savePath), decodeData);
	}
}
