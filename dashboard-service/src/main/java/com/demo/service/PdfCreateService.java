package com.demo.service;

import com.demo.domian.dto.ChemicalTable;
import com.demo.domian.dto.DashboardDTO;
import com.demo.domian.dto.ImporterTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PdfCreateService {

    byte[] createExportDeclarationPdf(
            DashboardDTO dashboardDTO, List<ImporterTable> importerList, List<ChemicalTable> chemicalList
            , HttpServletRequest request, HttpServletResponse response
    );

    byte[] createExportDeclarationPdf(DashboardDTO dashboardDTO, List<ImporterTable> importerList, List<ChemicalTable> chemicalList);

}
