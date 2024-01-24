package com.demo.web.rest;

import com.demo.domian.ApiResponse;
import com.demo.domian.DropDownResponse;
import com.demo.domian.dto.DashboardDTO;
import com.demo.service.PdfService;
import common.library.constants.app.FwUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * Controller for handling Export Declaration.
 *
 * @author santosh
 */
@RestController
@RequestMapping("/pdf")
public class PdfRestController {

    private static Logger logger = LoggerFactory.getLogger(PdfRestController.class);

    @Autowired
    PdfService pdfService;

    /**
     * Handles POST requests to "/pdf/drop-down" to get drop down of chemical and country list.
     * @return An ApiResponse with a success message if export is successful, or an error message otherwise.
     */
    @PostMapping(value = "/drop-down" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<DropDownResponse> getDropDownData(
            @RequestBody DashboardDTO dashboardDTO){
        ApiResponse<DropDownResponse> apiResponse = new ApiResponse<>();
        try{
            DropDownResponse dropDownResponse = null;
            // Check if the input parameters are not null and not empty
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                logger.info("/pdf/drop-down :: payload :"+dashboardDTO.toString());
                // Retrive the resource
                dropDownResponse = pdfService.getDropDownData(dashboardDTO);
                apiResponse.setPayload(dropDownResponse);
            }
        }catch (Exception e){
            logger.error("/pdf/drop-down :: error :",e);
            e.printStackTrace();
        }
        // Return Resonse containing chemical and country data list
        return apiResponse;
    }

    /**
     * Handles Post requests to "/pdf/export-declaration" to export data as a PDF.
     * @return An ApiResponse with a success message if export is successful, or an error message otherwise.
     */
    @PostMapping("/export-declaration")
    public ApiResponse<byte[]> createExportDeclarationPdf(@RequestBody DashboardDTO dashboardDTO, HttpServletRequest request, HttpServletResponse response){
        ApiResponse<byte[]> apiResponse = null;
        try{
            // Check if the input parameters are not null and not empty
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName())){
                logger.info("/pdf/export-declaration :: payload :"+dashboardDTO.toString());
                // Retive the pdf in bytes
                apiResponse = pdfService.createExportDeclarationPdf(dashboardDTO,request,response);
                logger.info("/pdf/export-declaration :: response :"+apiResponse.toString());
            }
        }catch (Exception e){
            logger.error("/pdf/export-declaration :: error :",e);
            e.printStackTrace();
        }
        // Return the Response containing pdf in bytes
        return apiResponse;
    }
}
