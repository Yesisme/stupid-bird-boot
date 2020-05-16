package com.epsoft.demo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://atnoce.com/?id=41
public class PDFTest {

    @Test
    public void testGenerator() throws Exception {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File("F:/insured.pdf")));

        document.open();

        document.add(new Paragraph("hello pdf"));

        document.close();
    }

    @Test
    public void testPdf(){
    	
    	Font font = null; 
        Document document = null;
        PdfWriter writer = null;
        try{
            document = new Document(PageSize.A4,36,36,126,36);
            writer = PdfWriter.getInstance(document, new FileOutputStream("E:/insured.pdf"));
            //打开文件
            document.open();
            
            //设置标题
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            font = new Font(bfChinese, 6, Font.BOLD);
            Paragraph paragraph = new Paragraph("浙江省（桐乡市）社会保险参保证明",new Font(bfChinese, 18, Font.BOLDITALIC));
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            
            paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
            paragraph.setIndentationLeft(12); //设置左缩进
            paragraph.setIndentationRight(12); //设置右缩进
            paragraph.setFirstLineIndent(24); //设置首行缩进
            paragraph.setLeading(20f); //行间距
            paragraph.setSpacingBefore(5f); //设置段落上空白
            paragraph.setSpacingAfter(10f); //设置段落下空白

            //添加内容
            document.add(paragraph);

            // 18列的表.
            PdfPTable table = new PdfPTable(18);
            table.setTotalWidth(90);
            table.setWidthPercentage(100); // 宽度100%填充
            table.setSpacingBefore(10f); // 前间距
            table.setSpacingAfter(10f); // 后间距

            List<PdfPRow> listRow = table.getRows();
         
            //行1
            PdfPCell cells1[] = new PdfPCell[18];
            PdfPRow row1 = new PdfPRow(cells1);

            //单元格
            cells1[0] = new PdfPCell(buildParagraph("姓名：张三",font));//单元格内容
            cells1[0].setMinimumHeight(12);
            cells1[0].setColspan(3);
			cells1[0].setBorderColor(BaseColor.BLACK);//边框验证
			cells1[0].setUseAscender(true);//可以设置居中
			cells1[0].setHorizontalAlignment(Element.ALIGN_LEFT);//靠左边
			cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
					
			//单元格
            cells1[3] = new PdfPCell(buildParagraph("社会保障号:330603199510025200",font));
            cells1[3].setMinimumHeight(12);
            cells1[3].setColspan(12);
			cells1[3].setBorderColor(BaseColor.BLACK);//边框验证
			cells1[3].setUseAscender(true);//可以设置居中
			cells1[3].setHorizontalAlignment(Element.ALIGN_LEFT);//靠左边
			cells1[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
			
          
            cells1[15] = new PdfPCell(buildParagraph("性别:女",font));
            cells1[15].setMinimumHeight(12);
            cells1[15].setColspan(3);
			cells1[15].setBorderColor(BaseColor.BLACK);//边框验证
			cells1[15].setUseAscender(true);//可以设置居中
			cells1[15].setHorizontalAlignment(Element.ALIGN_LEFT);//靠左边
			cells1[15].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
			
            //行2
            PdfPCell cells2[] = new PdfPCell[18];
            PdfPRow row2 = new PdfPRow(cells2);
            cells2[0] = new PdfPCell(buildParagraph("社会保险基本情况",new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED),10,Font.BOLD)));
            cells2[0].setMinimumHeight(12);
            cells2[0].setColspan(18);
            cells2[0].setUseAscender(true);//可以设置居中
            cells2[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells2[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            PdfPCell cells3[] = new PdfPCell[18];
            PdfPRow row3 = new PdfPRow(cells3);

            cells3[0] = new PdfPCell(buildParagraph("险种",font));
            cells3[0].setMinimumHeight(12);//设置列大小
            cells3[0].setColspan(3);
            cells3[0].setUseAscender(true);//可以设置居中
            cells3[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            cells3[3] = new PdfPCell(buildParagraph("养老保险",font));
            cells3[3].setMinimumHeight(12);
            cells3[3].setColspan(3);
            cells3[3].setUseAscender(true);//可以设置居中
            cells3[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            cells3[6] = new PdfPCell(buildParagraph("医疗保险",font));
            cells3[6].setMinimumHeight(12);
            cells3[6].setColspan(3);
            cells3[6].setUseAscender(true);//可以设置居中
            cells3[6].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[6].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            cells3[9] = new PdfPCell(buildParagraph("工伤保险",font));
            cells3[9].setMinimumHeight(12);
            cells3[9].setColspan(3);
            cells3[9].setUseAscender(true);//可以设置居中
            cells3[9].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[9].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            cells3[12] = new PdfPCell(buildParagraph("生育保险",font));
            cells3[12].setMinimumHeight(12);
            cells3[12].setColspan(3);
            cells3[12].setUseAscender(true);//可以设置居中
            cells3[12].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[12].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            cells3[15] = new PdfPCell(buildParagraph("失业保险",font));
            cells3[15].setMinimumHeight(12);
            cells3[15].setColspan(3);
            cells3[15].setUseAscender(true);//可以设置居中
            cells3[15].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[15].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            //第四行
            PdfPCell cells4[] = new PdfPCell[18];
            PdfPRow row4 = new PdfPRow(cells4);

            cells4[0] = new PdfPCell(buildParagraph("参保状态",font));
            cells4[0].setMinimumHeight(12);//设置列大小
            cells4[0].setColspan(3);
            cells4[0].setUseAscender(true);//可以设置居中
            cells4[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            cells4[3] = new PdfPCell(buildParagraph("参保缴费",font));
            cells4[3].setMinimumHeight(12);
            cells4[3].setColspan(3);
            cells4[3].setUseAscender(true);//可以设置居中
            cells4[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            cells4[6] = new PdfPCell(buildParagraph("参保缴费",font));
            cells4[6].setMinimumHeight(12);
            cells4[6].setColspan(3);
            cells4[6].setUseAscender(true);//可以设置居中
            cells4[6].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[6].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            cells4[9] = new PdfPCell(buildParagraph("参保缴费",font));
            cells4[9].setMinimumHeight(12);
            cells4[9].setColspan(3);
            cells4[9].setUseAscender(true);//可以设置居中
            cells4[9].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[9].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            cells4[12] = new PdfPCell(buildParagraph("参保缴费",font));
            cells4[12].setMinimumHeight(12);
            cells4[12].setColspan(3);
            cells4[12].setUseAscender(true);//可以设置居中
            cells4[12].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[12].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中


            cells4[15] = new PdfPCell(buildParagraph("参保缴费",font));
            cells4[15].setMinimumHeight(12);
            cells4[15].setColspan(3);
            cells4[15].setUseAscender(true);//可以设置居中
            cells4[15].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[15].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            //行3
           /* PdfPCell cells3[] = new PdfPCell[18];
            PdfPRow row3 = new PdfPRow(cells3);
            
            cells3[0] = new PdfPCell(buildParagraph("单位编号",font));
            cells3[0].setMinimumHeight(20);//设置列大小
            cells3[0].setColspan(7);
            cells3[0].setUseAscender(true);//可以设置居中
            cells3[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            
            cells3[7] = new PdfPCell(buildParagraph("单位名称",font));
            cells3[7].setMinimumHeight(20);
            cells3[7].setColspan(11);
            cells3[7].setUseAscender(true);//可以设置居中
            cells3[7].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells3[7].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            
            //第四行
            PdfPCell cells4[] = new PdfPCell[18];
            PdfPRow row4 = new PdfPRow(cells4);
            
            cells4[0] = new PdfPCell(buildParagraph("4132764734",font));
            cells4[0].setMinimumHeight(12);
            cells4[0].setColspan(7);
            cells4[0].setUseAscender(true);//可以设置居中
            cells4[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            cells4[7] = new PdfPCell(buildParagraph("桐乡市社会保险事业局",font));
            cells4[7].setMinimumHeight(12);
            cells4[7].setColspan(11);
            cells4[7].setUseAscender(true);//可以设置居中
            cells4[7].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells4[7].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中*/
			
            //第五行
            PdfPCell cells5[] = new PdfPCell[18];
            PdfPRow row5 = new PdfPRow(cells5);
            cells5[0] = new PdfPCell(buildParagraph("最近24月缴费情况(201610-201809)",new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED),10,Font.BOLD)));
            cells5[0].setMinimumHeight(12);
            cells5[0].setColspan(18);
            cells5[0].setUseAscender(true);//可以设置居中
            cells5[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells5[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
 
            //第六行
            PdfPCell cells6[] = new PdfPCell[18];
            PdfPRow row6 = new PdfPRow(cells6);
            
            cells6[0] = new PdfPCell(buildParagraph("年",font));
            cells6[0].setMinimumHeight(36);
            cells6[0].setColspan(2);
            cells6[0].setUseAscender(true);//可以设置居中
            cells6[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            
            cells6[2] = new PdfPCell(buildParagraph("月",font));
            cells6[2].setMinimumHeight(36);
            cells6[2].setUseAscender(true);//可以设置居中
            cells6[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            cells6[3] = new PdfPCell(buildParagraph("单位编号",font));
            cells6[3].setMinimumHeight(36);
            cells6[3].setColspan(2);
            cells6[3].setUseAscender(true);//可以设置居中
            cells6[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
			/* PdfPTable iTable =new PdfPTable(2);
			iTable.setTotalWidth(10);
			iTable.setWidthPercentage(100);
			List<PdfPRow> iTablelistRow = iTable.getRows();
			
			cells6[5] = new PdfPCell(iTable);
			cells6[5].setMinimumHeight(36);
			cells6[5].setColspan(2);
			cells6[5].setUseAscender(true);//可以设置居中
			cells6[5].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
			cells6[5].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
			
			//第一行
			PdfPCell iTableiCell1[] =new PdfPCell[2];	
			PdfPRow iTableRow1 = new PdfPRow(iTableiCell1);
			iTableiCell1[0] = new PdfPCell(buildParagraph("养老保险",font));
			iTableiCell1[0].setMinimumHeight(12);
			iTableiCell1[0].setColspan(2);
			iTableiCell1[0].setHorizontalAlignment(Element.ALIGN_CENTER);
			iTableiCell1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			//iTableiCell1[0].setFixedHeight((float)25.0);
			
			iTablelistRow.add(iTableRow1);
			
			PdfPCell iTableiCell2[] =new PdfPCell[2];
			PdfPRow iTableRow2 = new PdfPRow(iTableiCell2);
			iTableiCell2[0] = new PdfPCell(buildParagraph("单位缴费（元）",font));
			iTableiCell2[0].setMinimumHeight(24);
			iTableiCell2[0].setHorizontalAlignment(Element.ALIGN_CENTER);
			iTableiCell2[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
			//iTableiCell2[0].setFixedHeight((float)25.0);
			
			iTableiCell2[1] = new PdfPCell(buildParagraph("个人缴费（元）",font));
			iTableiCell2[1].setMinimumHeight(24);
			iTableiCell2[1].setHorizontalAlignment(Element.ALIGN_CENTER);
			iTableiCell2[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
			//iTableiCell2[1].setFixedHeight((float)25.0);
			
			iTablelistRow.add(iTableRow2);*/
        
            //cells6[5].setPadding(0);
            
            PdfPTable ylaTable = buildPdfPTable(2,10,100);
            
            List<PdfPRow> ylaListRow = ylaTable.getRows();
            
            cells6[5] = new PdfPCell(ylaTable);
			cells6[5].setMinimumHeight(36);
			cells6[5].setColspan(2);
			cells6[5].setUseAscender(true);//可以设置居中
			cells6[5].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
			cells6[5].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
				
			build("养老保险",font,ylaListRow);
			
			
			PdfPTable yliTable = buildPdfPTable(2,10,100);
            
            List<PdfPRow> yliListRow = yliTable.getRows();
            
            cells6[7] = new PdfPCell(yliTable);
            cells6[7].setMinimumHeight(36);
            cells6[7].setColspan(2);
            cells6[7].setUseAscender(true);//可以设置居中
            cells6[7].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[7].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            build("医疗保险",font,yliListRow);
            
            PdfPTable gsTable = buildPdfPTable(2,10,100);
            
            List<PdfPRow> gsListRow = gsTable.getRows();
            
            cells6[9] = new PdfPCell(gsTable);
            cells6[9].setMinimumHeight(36);
            cells6[9].setColspan(2);
            cells6[9].setUseAscender(true);//可以设置居中
            cells6[9].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[9].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            build("工商保险",font,gsListRow);
            

            PdfPTable syeTable = buildPdfPTable(2,10,100);
            
            List<PdfPRow> syeListRow = syeTable.getRows();
            
            cells6[11] = new PdfPCell(syeTable);
            cells6[11].setMinimumHeight(36);
            cells6[11].setColspan(2);
            cells6[11].setUseAscender(true);//可以设置居中
            cells6[11].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[11].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            build("失业保险",font,syeListRow);
           
            
            PdfPTable syuTable = buildPdfPTable(2,10,100);
            List<PdfPRow> syuListRow = syuTable.getRows();
            
            cells6[13] = new PdfPCell(syuTable);
            cells6[13].setMinimumHeight(36);
            cells6[13].setColspan(2);
            cells6[13].setUseAscender(true);//可以设置居中
            cells6[13].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[13].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            build("生育保险",font,syuListRow);

            cells6[15] = new PdfPCell(buildParagraph("缴费状态",font));
            cells6[15].setMinimumHeight(36);
            cells6[15].setColspan(1);
            cells6[15].setUseAscender(true);//可以设置居中
            cells6[15].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[15].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            
            cells6[16] = new PdfPCell(buildParagraph("备注",font));
            cells6[16].setMinimumHeight(36);
            cells6[16].setColspan(2);
            cells6[16].setUseAscender(true);//可以设置居中
            cells6[16].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells6[16].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                 
            //把行添加到集合
            listRow.add(row1);
            listRow.add(row2);
            listRow.add(row3);
            listRow.add(row4);
            listRow.add(row5);
            listRow.add(row6);
            for(int i=0;i<24;i++) {
            	LinkedList<Map<String, Object>> generateLinkedList = generateMonth();
            	PdfPCell cells[] = new PdfPCell[18];
                PdfPRow row = new PdfPRow(cells);
                
                cells[0] = new PdfPCell(buildParagraph(generateLinkedList.get(i).get("year").toString(),font));
                cells[0].setMinimumHeight(12);
                cells[0].setColspan(2);
                cells[0].setUseAscender(true);//可以设置居中
                cells[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                
                cells[2] = new PdfPCell(buildParagraph(generateLinkedList.get(i).get("month").toString(),font));
                cells[2].setMinimumHeight(12);
                cells[2].setUseAscender(true);//可以设置居中
                cells[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                cells[3] = new PdfPCell(buildParagraph("60123432",font));
                cells[3].setMinimumHeight(12);
                cells[3].setColspan(2);
                cells[3].setUseAscender(true);//可以设置居中
                cells[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                cells[5] = new PdfPCell(buildParagraph("  ",font));
                cells[5].setMinimumHeight(12);
                cells[5].setColspan(2);
                cells[5].setUseAscender(true);//可以设置居中
                cells[5].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[5].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                cells[7] = new PdfPCell(buildParagraph("  ",font));
                cells[7].setMinimumHeight(12);
                cells[7].setColspan(2);
                cells[7].setUseAscender(true);//可以设置居中
                cells[7].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[7].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                
                cells[9] = new PdfPCell(buildParagraph("  ",font));
                cells[9].setMinimumHeight(12);
                cells[9].setColspan(2);
                cells[9].setUseAscender(true);//可以设置居中
                cells[9].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[9].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                cells[11] = new PdfPCell(buildParagraph("  ",font));
                cells[11].setMinimumHeight(12);
                cells[11].setColspan(2);
                cells[11].setUseAscender(true);//可以设置居中
                cells[11].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[11].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                
                cells[13] = new PdfPCell(buildParagraph("  ",font));
                cells[13].setMinimumHeight(12);
                cells[13].setColspan(2);
                cells[13].setUseAscender(true);//可以设置居中
                cells[13].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[13].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                cells[15] = new PdfPCell(buildParagraph("  ",font));
                cells[15].setMinimumHeight(12);
                cells[15].setUseAscender(true);//可以设置居中
                cells[15].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[15].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                
                cells[16] = new PdfPCell(buildParagraph("  ",font));
                cells[16].setMinimumHeight(12);
                cells[16].setColspan(2);
                cells[16].setUseAscender(true);//可以设置居中
                cells[16].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[16].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                
                listRow.add(row);
            }
            //把表格添加到文件中
            document.add(table);

        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭文档
            if(document!=null){
                document.close();
            }

            if(writer!=null){
                //关闭书写器
                writer.close();
            }
        }
    }
    
    
    //创建表格
    private Paragraph buildParagraph(String name,Font font) {
    	Paragraph paragraph = new Paragraph(name,font);
    	return paragraph;
    }
    
    /**
     * @param totalWidth 总宽度（f）
     * @param WidthPercentage 填充范围 0-100
     * @param numColumns 生成列数
     * @return PdfPTable 表格
     */
    
    private PdfPTable buildPdfPTable(int numColumns,int totalWidth,int widthPercentage) {
    	PdfPTable iTable =new PdfPTable(numColumns);
		iTable.setTotalWidth(totalWidth);
		iTable.setWidthPercentage(widthPercentage);
		return iTable;
    }
    
    /**
     * @param pdfPCell 数组
     * @param arrIndex 操作的数组角标
     * @param name 字段名称
     * @param font 字体格式
     * @param colspan 合并的列数
     * @param iTablelistRow 行数
     * @param minimumHeight 列高
     */
	private void buildPdfPCell(PdfPCell[] pdfPCell,int arrIndex,String name,Font font,int minimumHeight,int colspan) {
		pdfPCell[arrIndex] = new PdfPCell(buildParagraph(name,font));
		pdfPCell[arrIndex].setMinimumHeight(minimumHeight);
		pdfPCell[arrIndex].setColspan(colspan);

		pdfPCell[arrIndex].setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPCell[arrIndex].setVerticalAlignment(Element.ALIGN_MIDDLE);
	}	
	
	private void build(String name,Font font, List<PdfPRow> iTablelistRow) {
		PdfPCell ylCell1[] =new PdfPCell[2];	
		PdfPRow ylaoRow1 = new PdfPRow(ylCell1);
		
		buildPdfPCell(ylCell1,0,name,font,12,2);
		
		PdfPCell ylCell2[] =new PdfPCell[2];	
		PdfPRow ylaoRow2 = new PdfPRow(ylCell2);
		
		buildPdfPCell(ylCell2,0,"单位缴费（元）",font,24,1);
		buildPdfPCell(ylCell2,1,"个人缴费（元）",font,24,1);
        
		iTablelistRow.add(ylaoRow1);
		iTablelistRow.add(ylaoRow2);
	} 
	
	private LinkedList<Map<String,Object>> generateMonth() {
		LinkedList<Map<String,Object>> linkedHashSet = new LinkedList<>();
		for(int i=0;i<24;i++) {
			Map<String,Object> map = new HashMap<String,Object>();	
			String year = String.valueOf(LocalDate.now().minusMonths(24).plusMonths(i).getYear());
        	String month = String.valueOf(LocalDate.now().minusMonths(24).plusMonths(i).getMonthValue());
        	if(Integer.valueOf(month)<10) {
        		month = "0"+String.valueOf(month);
        	}
        	map.put("year", year);
        	map.put("month", month);
        	linkedHashSet.add(map);
		}
		return linkedHashSet;		
	}
}

