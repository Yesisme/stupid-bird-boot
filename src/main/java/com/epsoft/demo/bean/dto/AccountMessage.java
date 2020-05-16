package com.epsoft.demo.bean.dto;

import java.util.Date;

import com.epsoft.demo.annotation.Value;
import com.epsoft.demo.bean.entity.Article;

public class AccountMessage {

	private Long id;
	
	@Value(value="paymentId",require=false)
	private String paymentId;
	
	private String GroupName;
	
	private String siCardNo;
	
	private String paymentName;
	
	private String paymentIdNo;
	
	private Long paymentAmount;
	
	private String ssTradeStatus;
	
	private Date ssTradeTime;
	
	private String bankSerialNumber;
	
	private String checkTradeStatus;
	
	private Date checkTime;
	
	private String remark;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getSiCardNo() {
		return siCardNo;
	}

	public void setSiCardNo(String siCardNo) {
		this.siCardNo = siCardNo;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentIdNo() {
		return paymentIdNo;
	}

	public void setPaymentIdNo(String paymentIdNo) {
		this.paymentIdNo = paymentIdNo;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getSsTradeStatus() {
		return ssTradeStatus;
	}

	public void setSsTradeStatus(String ssTradeStatus) {
		this.ssTradeStatus = ssTradeStatus;
	}

	public Date getSsTradeTime() {
		return ssTradeTime;
	}

	public void setSsTradeTime(Date ssTradeTime) {
		this.ssTradeTime = ssTradeTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankSerialNumber() {
		return bankSerialNumber;
	}

	public void setBankSerialNumber(String bankSerialNumber) {
		this.bankSerialNumber = bankSerialNumber;
	}

	public String getCheckTradeStatus() {
		return checkTradeStatus;
	}

	public void setCheckTradeStatus(String checkTradeStatus) {
		this.checkTradeStatus = checkTradeStatus;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AccountMessage [id=" + id + ", paymentId=" + paymentId + ", GroupName=" + GroupName + ", siCardNo=" + siCardNo + ", paymentName=" + paymentName + ", paymentIdNo=" + paymentIdNo + ", paymentAmount=" + paymentAmount + ", ssTradeStatus=" + ssTradeStatus + ", ssTradeTime=" + ssTradeTime + ", bankSerialNumber=" + bankSerialNumber + ", checkTradeStatus=" + checkTradeStatus + ", checkTime=" + checkTime + ", remark=" + remark + "]";
	}

}
