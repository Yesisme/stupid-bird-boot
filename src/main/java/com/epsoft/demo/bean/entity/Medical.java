package com.epsoft.demo.bean.entity;

import javax.xml.bind.annotation.XmlRootElement;

//https://blog.csdn.net/qq_32786873/article/details/71817149
@XmlRootElement(name = "body") 
//控制JAXB 绑定类中属性和字段的排序  
public class Medical {
	
	private String CRBCODE;
	
	private String PTNBDM;
	
	private String DATA;
	
	private String DETAILS;
	
	private String name;
	
	private String idNo;

	public String getCRBCODE() {
		return CRBCODE;
	}

	public void setCRBCODE(String cRBCODE) {
		CRBCODE = cRBCODE;
	}

	public String getPTNBDM() {
		return PTNBDM;
	}

	public void setPTNBDM(String pTNBDM) {
		PTNBDM = pTNBDM;
	}

	public String getDATA() {
		return DATA;
	}

	public void setDATA(String dATA) {
		DATA = dATA;
	}

	public String getDETAILS() {
		return DETAILS;
	}

	public void setDETAILS(String dETAILS) {
		DETAILS = dETAILS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
}
