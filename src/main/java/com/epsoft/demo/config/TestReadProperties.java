package com.epsoft.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Component
@ConfigurationProperties(prefix="ftp")
@RequestMapping("/read")
public class TestReadProperties {
	
		
		private String ip;
		
		private String user;
	
		private String password;
	
		
		public String getIp() {
			return ip;
		}


		public void setIp(String ip) {
			this.ip = ip;
		}


		public String getUser() {
			return user;
		}


		public void setUser(String user) {
			this.user = user;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		@GetMapping("properties")
		public String ReadProperties(){
			return ip+user+password;
		}
}
