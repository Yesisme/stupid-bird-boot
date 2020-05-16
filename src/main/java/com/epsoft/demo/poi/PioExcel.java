package com.epsoft.demo.poi;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



class CreatePoiExcel{
	
	public static void testCreatePoiExcel(){
		String[] title = {"id","name","sex"};
		
		//创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个工作表sheet
		HSSFSheet sheet = workbook.createSheet();
		//创建第一行
		HSSFRow row = sheet.createRow(0);
		//创建一个单元格
		HSSFCell cell = null;
		
		for (int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellValue(title[i]);
		}
		for (int i = 1; i < 10; i++) {
			HSSFRow nextRow = sheet.createRow(i);
			HSSFCell cell2 = nextRow.createCell(0);
			cell2.setCellValue("a"+i);
			cell2 = nextRow.createCell(1);
			cell2.setCellValue("user"+i);
			cell2 = nextRow.createCell(2);
			cell2.setCellValue("男");
		}
		
		File file = new File("F:/poi_test.xls");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testGetPoiExcel() {
		File file = new File("F:/poi_test.xls");
		try {
			//读取excel中的内容
			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRow = 0;
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRow;i<=lastRowNum;i++) {
				HSSFRow row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					HSSFCell cell = row.getCell(j);	
					String value = cell.getStringCellValue();
					System.out.println(value + " ");
				}
				System.out.println();
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testCreateHighPoiExcel(){
		String[] title = {"id","name","sex"};
		
		//创建一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建一个工作表sheet
		XSSFSheet sheet = workbook.createSheet();
		//创建第一行
		XSSFRow row = sheet.createRow(0);
		//创建一个单元格
		XSSFCell cell = null;
		
		for (int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellValue(title[i]);
		}
		for (int i = 1; i < 10; i++) {
			XSSFRow nextRow = sheet.createRow(i);
			XSSFCell cell2 = nextRow.createCell(0);
			cell2.setCellValue("a"+i);
			cell2 = nextRow.createCell(1);
			cell2.setCellValue("user"+i);
			cell2 = nextRow.createCell(2);
			cell2.setCellValue("男");
		}
		
		File file = new File("F:/poi_test.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class PioExcel {

	public static void main(String[] args) {
		//CreatePoiExcel.testCreatePoiExcel();
		CreatePoiExcel.testCreateHighPoiExcel();
		//CreatePoiExcel.testGetPoiExcel();
	}		
}

