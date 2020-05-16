package com.epsoft.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class FTPUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
	
	private static final String ip = PropertiesUtil.getProperties("ftp.ip");
	
	private static final String user =  PropertiesUtil.getProperties("ftp.user");
	
	private static final String password = PropertiesUtil.getProperties("ftp.password");
		
	/*@Autowired  此种方式注入不能引入静态变量
	private  FTPConfig ftpConfig;*/
				
	public static boolean uploadFile(List<File> fileList) throws IOException{
		
		/*FTPConfig ftp= new FTPConfig();
		ftp.setIp(ftpConfig.getIp());
		ftp.setPort(21);
		ftp.setUser(ftpConfig.getUser());
		ftp.setIp(ftpConfig.getPassword());*/
		
		logger.info(ip+"."+user+"."+password);
		//FtpEntity ftpEntity = new FtpEntity(ip,21,user,password);
		logger.info("开始连接ftp服务器");
		FTPUtil ftpUtil = new FTPUtil();
		String path="D:\\lym\\ftpServer\\img\\dmimgs";
		File file = new File(path);// 设置文件存储路径
		if(!file.exists()){        // 检测是否存在目录
			file.setWritable(true);
			file.mkdirs();
		}
		logger.info("路径3",JSON.toJSONString(path));
		boolean result = ftpUtil.uploadFile(path,fileList);
		logger.info("开始连接ftp服务器,结束上传,上传结果:{}",result);
		return result;
		
	} 
	private  boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if(connectServer(ip,21,user,password)){
        	 try {
        		ftpClient.changeWorkingDirectory(remotePath);
        		ftpClient.setBufferSize(1024);
        		ftpClient.setControlEncoding("UTF-8");
        		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        		ftpClient.enterLocalPassiveMode();
        		 for(File fileItem : fileList){
                     fis = new FileInputStream(fileItem);
                     ftpClient.storeFile(fileItem.getName(),fis);
                 }

             } catch (IOException e) {
                 logger.error("上传文件异常",e);
                 uploaded = false;
                 e.printStackTrace();
             } finally {
                 fis.close();
                 ftpClient.disconnect();
             }
         }
         return uploaded;
     }


    private  boolean connectServer(String ip,int port,String user,String pwd){
        boolean isSuccess = false;
        ftpClient = new FTPClient(); 
        try {
        	logger.info(ip+"."+user+"."+pwd);
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }
    
    private FTPClient ftpClient;

	public FTPClient getFtpClient() {
		return ftpClient;
	}
	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}   
}