package com.epsoft.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class PageHelperUtil {
	
	public static List listSplit(List list, Integer currentPage, Integer pageSize) {
		List newList = new ArrayList<>();
		int totalSize = list.size();
		int maxPage = PageHelperUtil.getTotalPage(list, currentPage, pageSize);
		currentPage = currentPage < 1 ? 1 : currentPage;
		if(currentPage>maxPage) {
			return new ArrayList<>();
		}
		int fromIndex = pageSize * (currentPage - 1);
		int toIndex = (pageSize * currentPage) > totalSize ? totalSize : (pageSize * currentPage);
		newList = list.subList(fromIndex, toIndex);
		return newList;
	}

	public static int getTotalPage(List list, Integer currentPage, Integer pageSize) {
		int maxPage = 0;
		int totalSize = list.size();
		if (totalSize % pageSize != 0) {
			maxPage = totalSize / pageSize + 1;
		} else {
			maxPage = totalSize / pageSize;
		}
		return maxPage;
	}
}
