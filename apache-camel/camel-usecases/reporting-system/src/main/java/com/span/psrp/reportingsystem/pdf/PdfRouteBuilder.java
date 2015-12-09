package com.span.psrp.reportingsystem.pdf;

import org.apache.camel.Exchange;

public class PdfRouteBuilder {

    public void processPdf(final Exchange exchange) {
        // process the pdf document
        ReportDetails reportDetails = exchange.getOut().getBody(ReportDetails.class);

        if (null != reportDetails) {
            GeneratePdfTable.preparePdf(reportDetails);
            System.out.println("Cus-Info " + reportDetails.getCustomerInfo());
            System.out.println("Acct-Info " + reportDetails.getAccountInfo());
        }
    }
}
