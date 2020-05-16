package com.epsoft.demo.principle.singleResponsebility;
/**
 * 单一职责 
 * @author hp
 *
 */
public class Test {

	public static void main(String[] args) {
		UpdateInfo ui = new UpdateInfo();
		ui.updataPersonInfo("张三", "328373429", true);
	}
}
