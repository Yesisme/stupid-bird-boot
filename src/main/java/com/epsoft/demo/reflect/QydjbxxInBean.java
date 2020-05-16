package com.epsoft.demo.reflect;

import java.util.HashMap;
import java.util.Map;

public class QydjbxxInBean extends HnCommonInBean {

	// 身份证
	private String intext;

	private String aae013;

	private Integer pageno;

	private Integer pagesize;

	public String getIntext() {
		return intext;
	}

	public void setIntext(String intext) {
		this.intext = intext;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		//反射获取对象三种
		QydjbxxInBean qy = new QydjbxxInBean();
		System.out.println(qy.getClass());
		System.out.println(qy.getClass().getName());
		
		
		Class qydj = QydjbxxInBean.class;
		System.out.println(qydj);
		System.out.println(qydj.getName());
		
		Class<?> qydjbxx = Class.forName("com.epsoft.demo.reflect.QydjbxxInBean");
		System.out.println(qydjbxx);
		System.out.println(qydjbxx.getName());
		
		System.out.println(qydjbxx==qydj);
	}
}