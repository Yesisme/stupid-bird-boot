package com.epsoft.demo.principle.openClose;

import lombok.extern.slf4j.Slf4j;
/**
 * 开闭原则，对扩展开放，对修改关闭，对底层的抽象类关闭，由实现类进行扩展
 * @author hp
 *
 */
@Slf4j
public class Test {

	public static void main(String[] args) {
		//得到原价
		IAppliance appliance = new IceBox("10", "冰箱", "300");
		String format = String.format("电器Id:%s,电器名称:%s,电器价格:%s", appliance.getId(),appliance.getName(),appliance.getPrice());
		System.out.println(format);
		
		//得到打折价
		IAppliance app = new DiscountPriceAppliance("10", "冰箱", "300");
		String formatapp = String.format("电器Id:%s,电器名称:%s,电器折扣价格:%s", app.getId(),app.getName(),app.getPrice());
		System.out.println(formatapp);
		
		//得到原价和打折价
		DiscountPriceAppliance dis = (DiscountPriceAppliance) app;
		String formatdis = String.format("电器Id:%s,电器名称:%s,电器折扣价格:%s，电器原价:%s", dis.getId(),dis.getName(),dis.getPrice(),dis.getOriginPrice());
		System.out.println(formatdis);
	}
}
