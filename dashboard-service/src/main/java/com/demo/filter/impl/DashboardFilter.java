package com.demo.filter.impl;

import com.demo.util.constants.Constants;

public class DashboardFilter extends MetadataFilterImpl {

    @Override
    public String doFilter(String key) throws Exception {
        switch (key.toUpperCase()){
            case Constants.CHEMICAL_NAME:
                return "c.name";
            case Constants.CHEMICAL_CODE:
                return "t.ritc_code";
            case Constants.TOTAL_IMPORTER:
                return "totalImporter";
            case Constants.IMPORTER_ID:
                return "i.id";
            case Constants.IMPORTER_NAME:
                return "i.name";
            case Constants.CHEMICAL_COUNT:
                return "chemicalCount";
            case Constants.TOTAL_QUANTITY:
                return "totalQuantity";
            case Constants.TOTAL_AMOUNT:
                return "totalAmount";
            default:
                throw  new Exception("Invalid Search Filter");
        }
    }
}
