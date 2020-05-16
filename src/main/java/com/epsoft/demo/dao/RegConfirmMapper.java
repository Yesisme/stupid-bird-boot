package com.epsoft.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.epsoft.demo.bean.po.RegConfirm;

public interface RegConfirmMapper {

	Long insertConfirm(RegConfirm regConfirm);
	
	Long getHosId(@Param("param1") String param1);
	
	RegConfirm selectByHosId(@Param("hosId") Long hosId);
}
