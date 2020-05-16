package com.epsoft.demo.rpc.one;

public class ChinaService implements IService{

	@Override
	public String say(String name) {
		return name.concat("你好!");
	}

}
