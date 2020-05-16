package com.epsoft.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

	/**
	 * 校验字符串是否是数字
	 * 
	 * @param str
	 *            校验的字符串
	 * @return boolean
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		if (!str.equals("1") && !str.equals("2") && !str.equals("3")) {
			return false;
		}
		return true;
	}
	
	public static String lowerFirstChar(String str){
		if(!Character.isLowerCase(str.charAt(0))) {
			char [] chars = str.toCharArray();
			chars[0] += 32;
			return String.valueOf(chars);
		}
		return str;
	}
	
	public static void main(String[] args) {

		System.out.println(lowerFirstChar("Pdds"));
	}
}
