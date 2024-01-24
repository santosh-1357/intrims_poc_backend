package com.demo.service.impl;

import com.demo.domian.dto.ChemicalTable;
import com.demo.domian.dto.DashboardDTO;
import com.demo.domian.dto.ImporterTable;
import com.demo.service.PdfCreateService;
import com.demo.util.HeaderFooterPageEvent;
import com.demo.util.PdfHelper;
import com.demo.util.constants.PdfStaticDataConstants;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.library.constants.app.FwUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PdfCreateServiceImpl implements PdfCreateService, PdfStaticDataConstants {

    @Value("${pdf.path.export_declaration}")
    private String exportDeclarationPdfPath;

    @Override
    public byte[] createExportDeclarationPdf(
            DashboardDTO dashboardDTO, List<ImporterTable> importerList, List<ChemicalTable> chemicalList,
            HttpServletRequest request, HttpServletResponse response) {

        byte[] bytes = new byte[0];
        Document document = new Document();
        float doctopMargin = 50f; // Set the top margin
        float docbottomMargin = 50f; // Set the bottom margin
        // Margin to whole document
        document.setMargins(document.leftMargin(), document.rightMargin(), doctopMargin, docbottomMargin);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY-HH-mm");
        // Output path to generate pdf
        String outputPath = "D:\\Parent_project_directory\\PDF_Form\\Export\\dynamic-output_" + dateFormat.format(new Date()) + ".pdf";
        try{
            // Check if the provided parameters are not null or empty
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName())
                    && FwUtils.isNotNullOrNotEmpty(importerList) &&  FwUtils.isNotNullOrNotEmpty(chemicalList)){

                DateFormat df = new SimpleDateFormat("YYYY");
                String financialYear = df.format(df.parse(dashboardDTO.getFromDate()));
                // Set up PDF writer with header and footer
                PdfWriter writer = PdfWriter.getInstance(document, baos);
                HeaderFooterPageEvent pageEvent = new HeaderFooterPageEvent(EXPORT_DECLARATION_HEADER, EXPORT_DECLARATION_FOOTER);
                writer.setPageEvent(pageEvent);

                // Open the document
                document.open();
                // Add content to the document
                document.add(
                        PdfHelper.getBoldParagraph(
                                EXPORT_DECLARATION_FIRST_PARAGRAPH
                                        .replace("exporter_name", dashboardDTO.getExporterName())
                        )
                );
                document.add(
                        PdfHelper.getParagraph(
                                EXPORT_DECLARATION_SECOND_PARAGRAPH
                                        .replace("from_date", dashboardDTO.getFromDate())
                                        .replace("to_date", dashboardDTO.getToDate())
                                        .replace("country_name", dashboardDTO.getImporterCountry())
                                        .replace("chemical_name", dashboardDTO.getChemicalName())
                                        .replace("financial_year", financialYear)
                        )
                );

                // Create and add the importer table to the document
                PdfPTable importerTable = PdfHelper.getPdfTable(
                        IMPORTER_TABLE_COLUMN_HEADER,
                        IMPORTER_TABLE_COLUMN_WIDTHS
                );
                if(Objects.nonNull(importerTable)){
                    importerList.forEach(
                            (importer) ->{
                                importerTable.addCell(importer.getImporterName());
                                importerTable.addCell(importer.getImporterCountry());
                                importerTable.addCell(importer.getDateOfTransaction());
                                importerTable.addCell(importer.getExportReference());
                            }
                    );
                    // Add the importer table to the document
                    document.add(importerTable);
                }

                //second page
                document.newPage();
                document.add(
                        PdfHelper.getParagraph(
                                EXPORT_DECLARATION_THIRD_PARAGRAPH
                                        .replace("financial_year", financialYear)
                        )
                );

                // Create and add the chemical table to the document
                PdfPTable chemicalTable = PdfHelper.getPdfTable(
                        CHEMICAL_TABLE_COLUMN_HEADER,
                        CHEMICAL_TABLE_COLUMN_WIDTHS
                );
                if(Objects.nonNull(chemicalTable)){
                    int i=0;
                    for (ChemicalTable chemical : chemicalList){
                        i++;
                        chemicalTable.addCell(""+i);
                        chemicalTable.addCell(chemical.getCasNumber());
                        chemicalTable.addCell(chemical.getEcNumber());
                        chemicalTable.addCell(chemical.getRegion());
                        chemicalTable.addCell(chemical.getRegistrationNumber());
                        chemicalTable.addCell(chemical.getInitialTonnage());
                        chemicalTable.addCell(chemical.getExportStatus());
                        chemicalTable.addCell(chemical.getIncreaseTonnage());
                        chemicalTable.addCell(chemical.getTonnageBand());
                    }
                }
                // Add the chemical table to the document
                document.add(chemicalTable);

                // Add the final content to the document
                document.add(
                        PdfHelper.getParagraph(
                                EXPORT_DECLARATION_FORTH_PARAGRAPH
                                        .replace("authorised_person","Raghvendra Sattigeri")
                                        .replace("exporter_name", dashboardDTO.getExporterName())
                        )
                );
                //close the document
                document.close();

                // Set response headers for PDF download
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control",
                        "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                response.setContentType("application/pdf");
                response.setContentLength(baos.size());

                // Write the generated PDF to the response output stream
//                OutputStream os = response.getOutputStream();
//                baos.writeTo(os);
//                os.flush();
//                os.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
        }
        // Return the generated byte array representing the PDF
        return baos.toByteArray();
    }

    @Override
    public byte[] createExportDeclarationPdf(DashboardDTO dashboardDTO, List<ImporterTable> importerList, List<ChemicalTable> chemicalList) {
        byte[] bytes = new byte[0];
        Document document = new Document();
        float doctopMargin = 50f; // Set the top margin
        float docbottomMargin = 50f; // Set the bottom margin
        document.setMargins(document.leftMargin(), document.rightMargin(), doctopMargin, docbottomMargin);
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD_MM_YYYY_HH_mm");
        String outputPath = exportDeclarationPdfPath+"export_declaration_" + dateFormat.format(new Date()) + ".pdf";
        try{
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName())
                    && FwUtils.isNotNullOrNotEmpty(importerList) &&  FwUtils.isNotNullOrNotEmpty(chemicalList)){

                DateFormat df = new SimpleDateFormat("YYYY");
                String financialYear = df.format(df.parse(dashboardDTO.getFromDate()));
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                HeaderFooterPageEvent pageEvent = new HeaderFooterPageEvent(EXPORT_DECLARATION_HEADER, EXPORT_DECLARATION_FOOTER);
                writer.setPageEvent(pageEvent);

                document.open();
                document.add(
                        PdfHelper.getBoldParagraph(
                                EXPORT_DECLARATION_FIRST_PARAGRAPH
                                        .replace("exporter_name", dashboardDTO.getExporterName())
                        )
                );
                document.add(
                        PdfHelper.getParagraph(
                                EXPORT_DECLARATION_SECOND_PARAGRAPH
                                        .replace("from_date", dashboardDTO.getFromDate())
                                        .replace("to_date", dashboardDTO.getToDate())
                                        .replace("country_name", dashboardDTO.getImporterCountry())
                                        .replace("chemical_name", dashboardDTO.getChemicalName())
                                        .replace("financial_year", financialYear)
                        )
                );

                PdfPTable importerTable = PdfHelper.getPdfTable(
                        IMPORTER_TABLE_COLUMN_HEADER,
                        IMPORTER_TABLE_COLUMN_WIDTHS
                );
                if(Objects.nonNull(importerTable)){
                    importerList.forEach(
                            (importer) ->{
                                importerTable.addCell(importer.getImporterName());
                                importerTable.addCell(importer.getImporterCountry());
                                importerTable.addCell(importer.getDateOfTransaction());
                                importerTable.addCell(importer.getExportReference());
                            }
                    );
                    // Add the importer table to the document
                    document.add(importerTable);
                }

                //second page
                document.newPage();
                document.add(
                        PdfHelper.getParagraph(
                                EXPORT_DECLARATION_THIRD_PARAGRAPH
                                        .replace("financial_year", financialYear)
                        )
                );

                PdfPTable chemicalTable = PdfHelper.getPdfTable(
                        CHEMICAL_TABLE_COLUMN_HEADER,
                        CHEMICAL_TABLE_COLUMN_WIDTHS
                );
                if(Objects.nonNull(chemicalTable)){
                    int i=0;
                    for (ChemicalTable chemical : chemicalList){
                        i++;
                        chemicalTable.addCell(""+i);
                        chemicalTable.addCell(chemical.getCasNumber());
                        chemicalTable.addCell(chemical.getEcNumber());
                        chemicalTable.addCell(chemical.getRegion());
                        chemicalTable.addCell(chemical.getRegistrationNumber());
                        chemicalTable.addCell(chemical.getInitialTonnage());
                        chemicalTable.addCell(chemical.getExportStatus());
                        chemicalTable.addCell(chemical.getIncreaseTonnage());
                        chemicalTable.addCell(chemical.getTonnageBand());
                    }
                }
                // Add the chemical table to the document
                document.add(chemicalTable);

                document.add(
                        PdfHelper.getParagraph(
                                EXPORT_DECLARATION_FORTH_PARAGRAPH
                                        .replace("authorised_person","Raghvendra Sattigeri")
                                        .replace("exporter_name", dashboardDTO.getExporterName())
                        )
                );
                document.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
        }
        return new byte[0];
    }
}
