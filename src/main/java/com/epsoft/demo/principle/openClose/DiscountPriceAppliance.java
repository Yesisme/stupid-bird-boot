package com.epsoft.demo.principle.openClose;

public class DiscountPriceAppliance extends IceBox{

	public DiscountPriceAppliance(String id, String name, String price) {
		super(id, name, price);
	}

	public String getOriginPrice() {
		return super.getPrice();
	}
	
	@Override
	public String getPrice() {
		return String.valueOf(Double.valueOf(super.getPrice())*0.8);
	}

}
