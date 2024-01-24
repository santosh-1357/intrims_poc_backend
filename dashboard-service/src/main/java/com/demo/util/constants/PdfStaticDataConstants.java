package com.demo.util.constants;

public interface PdfStaticDataConstants extends PdfTableConstants {

    /**
     * export-declaration pdf startic data --start--
     * */
    String EXPORT_DECLARATION_HEADER = "Export Declarations";

    String EXPORT_DECLARATION_FOOTER = "Submitted through the GPC (Europe) Export Declaration Online Portal";
    String EXPORT_DECLARATION_FIRST_PARAGRAPH = "\nDeclaration of company exports by: exporter_name";

    String EXPORT_DECLARATION_SECOND_PARAGRAPH = "\nFrom Date : from_date" +
            "\nTo Date : to_date" +
            "\nCountryName : country_name" +
            "\nChemicalName : chemical_name" +
            "\nThis declaration is required by the Only Representative (within the meaning of REACH Regulation) " +
            "to comply with the REACH requirements on the company's behalf." +
            "\nFollowing is the declaration by our company of the exports of all substances and " +
            "all buyers being represented by Global Product Compliance (Europe) AB as the Only Representative, " +
            "for the period between financial_year.\nOur company has during the above said period exported to the following buyers " +
            "[whenever there is a new buyer (not included in the table below), kindly add the new buyer details in the table]:" +
            "\n";


    String EXPORT_DECLARATION_THIRD_PARAGRAPH = "\nBased upon the above listed exports and the cumulative total tonnage of exports of respected substances," +
            "\nColumn Export Status in the following table indicates if the substance has been exported or not during the period (calendar year : financial_year )" +
            "\nColumn Increase Tonnage Band indicates if the tonnage band, in comparison to the earlier declared tonnage band, " +
            "(as is provided in column Initial Tonnage Band) has increased or not. " +
            "\nColumn Tonnage Band Quantity (MT) indicates the tonnage band quantity of registered substances.";

    String EXPORT_DECLARATION_FORTH_PARAGRAPH = "In case, post registration, in any of the years, " +
            "if the quantity of exports exceeds the registered tonnage band, Global Product Compliance (Europe) AB would not be " +
            "responsible for this quantity over and above the registered tonnage quantity for EU direct exports / Non EU indirect exports." +
            "\n\nWe hereby confirm that all the information provided above is correct." +
            "\n\nSigned and Certified," +
            "\nauthorised_person," +
            "\nexporter_name.";

    /*
     * export-declaration pdf static data --end--
     * */
}
