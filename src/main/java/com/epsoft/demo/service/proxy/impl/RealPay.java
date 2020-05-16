package com.epsoft.demo.service.proxy.impl;

import com.epsoft.demo.service.proxy.Payment;

public class RealPay implements Payment{

	@Override
	public void pay() {
		System.out.println("我是用户，我只关心我付了多少钱");
	}

}
