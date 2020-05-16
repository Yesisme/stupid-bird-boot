package com.epsoft.demo.principle.openClose;

public class IceBox implements IAppliance{
	
	private String id;
	
	private String name;
	
	private String price;
	
	public IceBox(String id, String name, String price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		
		return this.name;
	}

	@Override
	public String getPrice() {

		return this.price;
	}

}
