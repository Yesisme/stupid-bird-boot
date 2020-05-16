package com.epsoft.demo.bean.po;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="reg_confirm")
public class RegConfirm {

	private Long id;
	
	@Column(name="HOS_ID")
	private Long hosId;
	
	private String patientNo;
	
	private String accessDate;
	
	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHosId() {
		return hosId;
	}

	public void setHosId(Long hosId) {
		this.hosId = hosId;
	}

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

}
