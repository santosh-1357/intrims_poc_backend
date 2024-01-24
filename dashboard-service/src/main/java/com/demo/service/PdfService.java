package com.demo.service;

import com.demo.domian.ApiResponse;
import com.demo.domian.DropDownResponse;
import com.demo.domian.dto.DashboardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PdfService {

    DropDownResponse getDropDownData(DashboardDTO dashboardDTO) throws Exception;
    ApiResponse<byte[]> createExportDeclarationPdf(DashboardDTO dashboardDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
