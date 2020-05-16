package com.epsoft.demo.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class PropertiesLoaderUtil {

	public static void doScan(List<Object> list,String basePackage) {
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String replacePackage = basePackage.replace('.', '/');
		URL uri = cl.getResource(replacePackage);
		String localPath = uri.getFile();
		File file = new File(localPath);
		List<String> fileList = getFileList(file);
		for (String str : fileList) {
			String newPackge = basePackage+"."+str;
			if(str.endsWith(".class")) {
				list.add(newPackge.substring(0,newPackge.indexOf(".class")));
			}else {
				doScan(list,newPackge);
			}
		}
	}
	
	private static List<String> getFileList(File file){
		String[] list = file.list();
		return Arrays.asList(list);
	} 
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		PropertiesLoaderUtil.doScan(list, "com.epsoft.demo");
		list.stream().forEach(s->System.out.println(s));
	}
}
