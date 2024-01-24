package com.demo.util.constants;

public interface PdfTableConstants {


    // Export-declaration pdf Importer Table --start--
    String[] IMPORTER_TABLE_COLUMN_HEADER = {
            "European Buyer Name / Non-EU indirect exporter",
            "Country",
            "Date Of Transaction",
            "Export Reference"
    };

    float[] IMPORTER_TABLE_COLUMN_WIDTHS = {
            250f,
            200f,
            450f,
            600f
    };
    // Export-declaration pdf Importer Table --end--

    // Export-declaration pdf Chemical Table --start--
    String[] CHEMICAL_TABLE_COLUMN_HEADER = {
            "Sr.No.",
            "CAS\nNumber",
            "EC\nNumber",
            "Region",
            "Registration No./\nDuin No./\nPre-registration No.",
            "Initial\nTonnage\nBand",
            "Export\nStatus",
            "Total Quantity\n(MT)",
            "Tonnage Band\nQuantity\n( MT )"
    };

    float[] CHEMICAL_TABLE_COLUMN_WIDTHS = {
            200f,
            250f,
            250f,
            250f,
            550f,
            250f,
            250f,
            250f,
            250f
    };
    // Export-declaration pdf Chemical Table --end--
}
