package com.epsoft.demo.principle.singleResponsebility;

public class UpdateInfo {

	public void updateName(String name) {
		
		System.out.println("变更"+name);
	}
	
	public void updateIdNo(String idNo) {
		System.out.println("变更"+idNo);
	}
	
	public void updataPersonInfo(String name,String idNo,boolean boo) {
		if(boo) {
			updateName(name);
		}else {
			updateIdNo(idNo);
		}
	}
}
