package com.epsoft.demo.service.proxy.impl;

import com.epsoft.demo.service.proxy.Payment;

public class AliPay implements Payment{
	
	private Payment payment;
	
	public AliPay(Payment payment) {
		this.payment=payment;
	}
	
	public void beforePay() {
		System.out.println("从工商银行取钱");
	}

	@Override
	public void pay() {
		beforePay();
		payment.pay();
		afterPay();
	}
	
	public void afterPay() {
		System.out.println("付钱给乐一明");
	}

}
