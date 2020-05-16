package com.epsoft.demo.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;

public class MegerCell {


       public static void main(String[] args) {
            String tmpPath ="E:\\test.pdf";
            PdfPCell cell;
            PdfPCell iCell;
            PdfPTable iTable;
            float lineHeight1 = (float)25.0;
            float lineHeight2 = (float)25.0;
           try{
                Document pdfDoc =new Document(PageSize.A4.rotate(), 36, 36, 24, 36);

                PdfWriter.getInstance(pdfDoc, new FileOutputStream(tmpPath));

                pdfDoc.open();

                PdfPTable headerTable =new PdfPTable(2);
                headerTable.setWidthPercentage(40);

                //create a table to fill cell 1
                iTable =new PdfPTable(2);
                iCell =new PdfPCell(new Paragraph("A"));
                iCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                iCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                iCell.setFixedHeight(lineHeight1);
                //merge column
                iCell.setColspan(2);
                iTable.addCell(iCell);
                iCell =new PdfPCell(new Paragraph("B"));
                iCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                iCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                iCell.setFixedHeight(lineHeight2);
                iTable.addCell(iCell);
                iCell =new PdfPCell(new Paragraph("C"));
                iCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                iCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                iCell.setFixedHeight(lineHeight2);
                iTable.addCell(iCell);

                cell =new PdfPCell(iTable);
                cell.setPadding(0);
                headerTable.addCell(cell);

                 //fill cell 2
                 cell =new PdfPCell(new Paragraph("D"));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setPadding(0);
                 cell.setFixedHeight(lineHeight1+lineHeight2);
                 headerTable.addCell(cell);

                 pdfDoc.add(headerTable);

                pdfDoc.close();

                 } catch(Exception e) {
                 e.printStackTrace();
                }
             }
}
