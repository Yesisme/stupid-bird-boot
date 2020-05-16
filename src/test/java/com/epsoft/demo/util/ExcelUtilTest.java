package com.epsoft.demo.util;

import com.alibaba.excel.metadata.Sheet;
import com.epsoft.demo.bean.model.TableHeaderExcelProperty;
import com.epsoft.demo.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ExcelUtilTest {

    //获取excel种的全部数据
    @Test
    public void testExcel(){
		String filePath = "F:\\用户.xlsx";
		List<Object> objects = ExcelUtil.readLessThan1000Row(filePath);
		objects.stream().forEach(s-> System.out.println(s));
    }


    //获取第一个sheet,sheetNo表示哪个sheet,headLineMun表示从哪一行开始获取，0为第一个行
    @Test
    public void testSheetLessThan1000(){
        String filePath = "F:\\用户.xlsx";
        Sheet sheet1 = new Sheet(2,2);
        List<Object> objects = ExcelUtil.readLessThan1000RowBySheet(filePath, sheet1);
        objects.stream().forEach(s-> System.out.println(s));
    }

    //大于100行
    @Test
    public void testSheetMoreThan1000(){
        Long start = System.currentTimeMillis();
        String filePath = "F:\\用户.xlsx";
        Sheet sheet1 = new Sheet(3,1);
        List<Object> objects = ExcelUtil.readMoreThan1000RowBySheet(filePath, sheet1);
        Long end = System.currentTimeMillis();
        log.info("[{}]",end-start);
        objects.stream().forEach(s-> System.out.println(s));
        Long end1 = System.currentTimeMillis();
        log.info("[{}]",end1-start);
    }


    //写入excel
    @Test
    public void writeExcel(){
        Long start = System.currentTimeMillis();
        String filePath = "F:\\学生.xlsx";
        ArrayList<TableHeaderExcelProperty> data = new ArrayList<>();
        for(int i=0;i<5;i++){
            TableHeaderExcelProperty table = new TableHeaderExcelProperty();
            table.setName("excel"+i);
            table.setAge(i);
            table.setSchool("excel"+i);
            data.add(table);
        }
        ExcelUtil.writeWithTemplate(filePath,data);
        Long end = System.currentTimeMillis();
        log.info("[{}]",end-start);
    }

    /**
     * 生成excle, 带用模型,带多个sheet
     */
    @org.junit.Test
    public void writeWithMultipleSheel(){
        ArrayList<ExcelUtil.MultipleSheelPropety> list1 = new ArrayList<>();
        for(int j = 1; j < 4; j++){
            ArrayList<TableHeaderExcelProperty> list = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
                tableHeaderExcelProperty.setName("cmj" + i);
                tableHeaderExcelProperty.setAge(22 + i);
                tableHeaderExcelProperty.setSchool("清华大学" + i);
                list.add(tableHeaderExcelProperty);
            }

            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("sheet" + j);

            ExcelUtil.MultipleSheelPropety multipleSheelPropety = new ExcelUtil.MultipleSheelPropety();
            multipleSheelPropety.setData(list);
            multipleSheelPropety.setSheet(sheet);

            list1.add(multipleSheelPropety);

        }

        ExcelUtil.writeWithMultipleSheel("/home/chenmingjian/Downloads/aaa.xlsx",list1);

    }
}