package com.epsoft.demo.principle.dependenceInversion;

import org.springframework.stereotype.Service;

@Service("iceBox")
public class IceBox implements Appliance{

	@Override
	public void produceAppliance() {
		System.out.println("只负责生产冰箱");
	}

}
