package com.epsoft.demo.bean.vo;

import java.util.List;

public class Contribution {

	private Integer year;
	
	private Integer month;
	
	private String insuranceGroupNo;
	
	private String paymentStatus;
	
	private String Remarks;
	
	private List<Insuranceprofile> Insuranceprofile;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getInsuranceGroupNo() {
		return insuranceGroupNo;
	}

	public void setInsuranceGroupNo(String insuranceGroupNo) {
		this.insuranceGroupNo = insuranceGroupNo;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public List<Insuranceprofile> getInsuranceprofile() {
		return Insuranceprofile;
	}

	public void setInsuranceprofile(List<Insuranceprofile> insuranceprofile) {
		Insuranceprofile = insuranceprofile;
	}
	
}

class Insuranceprofile {
	
	private String Insuranceprofile;
	
	private String insurancebase;
	
	private String insurancefee;
	
	private String areacode;

	public String getInsuranceprofile() {
		return Insuranceprofile;
	}

	public void setInsuranceprofile(String insuranceprofile) {
		Insuranceprofile = insuranceprofile;
	}

	public String getInsurancebase() {
		return insurancebase;
	}

	public void setInsurancebase(String insurancebase) {
		this.insurancebase = insurancebase;
	}

	public String getInsurancefee() {
		return insurancefee;
	}

	public void setInsurancefee(String insurancefee) {
		this.insurancefee = insurancefee;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

}
