package com.demo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileOutputStream;

@NoArgsConstructor
@AllArgsConstructor
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    private String header;
    private String footer;

    public void onStartPage(PdfWriter writer, Document document) {
        addHeader(writer, document);
    }

    public void onEndPage(PdfWriter writer, Document document) {
        addFooter(writer, document);
    }


    private void addHeader(PdfWriter writer, Document document) {
        Font font = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD, BaseColor.GRAY);
        Chunk chunk = new Chunk(header, font);

        PdfContentByte canvas = writer.getDirectContent();
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(chunk), document.left(), document.top(), 0);
    }

    private void addFooter(PdfWriter writer, Document document) {
        try {
            PdfContentByte canvas = writer.getDirectContent();

            Font footerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.GRAY);
            Font pageNumberFont = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL);

            float xPosition = document.leftMargin(); // X-coordinate position
            float yPosition = document.bottom() + 30; // Y-coordinate position

            // Separator line
            canvas.moveTo(xPosition, yPosition);
            canvas.lineTo(document.getPageSize().getWidth() - document.rightMargin(), yPosition);
            canvas.setColorStroke(BaseColor.BLACK);
            canvas.stroke();

            // Footer content
            Chunk footerContent = new Chunk(footer, footerFont);
            float textWidth = footerFont.getCalculatedBaseFont(true).getWidthPoint(footerContent.toString(), footerFont.getSize());
            float centeredXPosition = (document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - textWidth) / 2 + document.leftMargin();
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(footerContent), centeredXPosition, yPosition - 15, 0);

            // Page number
            Chunk pageNumber = new Chunk("Page " + writer.getPageNumber(), pageNumberFont);
            ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(pageNumber), document.getPageSize().getWidth() - document.rightMargin(), yPosition - 15, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
