package com.demo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import common.library.constants.app.FwUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PdfHelper {

    private static Logger logger = LoggerFactory.getLogger(PdfHelper.class);

    public static Paragraph getParagraph(String... args){
        Paragraph additionalText = new Paragraph();
        additionalText.setAlignment(Element.ALIGN_LEFT);
        if(FwUtils.isNotNullOrNotEmpty(Collections.singletonList(args))){
            for (String stringArg: args) {
                additionalText.add(new Chunk(stringArg, new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            }
        }
        return additionalText;
    }

    public static Paragraph getBoldParagraph(String... args){
        Paragraph additionalText = new Paragraph();
        additionalText.setAlignment(Element.ALIGN_LEFT);
        if(FwUtils.isNotNullOrNotEmpty(Collections.singletonList(args))){
            for (String stringArg:args) {
                additionalText.add(new Chunk(stringArg, new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
            }
        }
        return additionalText;

    }

    public static PdfPTable getPdfTable(String[] tableHeaders, float[] columnWidths){
        float tableWidth = 520; // Set the width of the table
        PdfPTable pdfTable = null;
        try{
            if(FwUtils.isNotNullOrNotEmpty(Collections.singletonList(tableHeaders))
                    && FwUtils.isNotNullOrNotEmpty(Collections.singletonList(columnWidths))){
                BaseColor baseColor = new BaseColor(192, 192, 192);
                pdfTable = new PdfPTable(tableHeaders.length);
                pdfTable.setLockedWidth(true);
                pdfTable.setTotalWidth(tableWidth);
                pdfTable.setWidths(columnWidths);
                pdfTable.setSpacingBefore(20f);
                pdfTable.setSpacingAfter(20f);
                for (String header: tableHeaders) {
                    pdfTable.addCell(createCell(header, baseColor));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pdfTable;
    }

    private static PdfPCell createCell(String content, BaseColor backgroundColor) {
        PdfPCell pdfPCell = new PdfPCell(new Phrase(content));
        pdfPCell.setBackgroundColor(backgroundColor);
        return pdfPCell;
    }
}
