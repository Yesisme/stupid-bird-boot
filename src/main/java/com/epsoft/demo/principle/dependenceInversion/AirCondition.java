package com.epsoft.demo.principle.dependenceInversion;

import org.springframework.stereotype.Service;

@Service
public class AirCondition implements Appliance{

	@Override
	public void produceAppliance() {
		System.out.println("支负责生产空调");
	}

}
