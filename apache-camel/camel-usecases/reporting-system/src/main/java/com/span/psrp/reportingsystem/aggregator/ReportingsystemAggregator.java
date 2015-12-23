package com.span.psrp.reportingsystem.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.span.psrp.reportingsystem.model.AccountInfo;
import com.span.psrp.reportingsystem.model.CustomerInfo;
import com.span.psrp.reportingsystem.pdf.ReportDetails;

public class ReportingsystemAggregator implements AggregationStrategy {

    ReportDetails reportDetails = new ReportDetails();

    @Override
    public Exchange aggregate(final Exchange oldExchange, final Exchange newExchange) {
        if ((null != oldExchange) && (null != newExchange)) {
            if ((oldExchange != null) || (oldExchange.getIn() != null)) {
                CustomerInfo customerInfo = oldExchange.getIn().getBody(CustomerInfo.class);
                AccountInfo accountInfo = oldExchange.getIn().getBody(AccountInfo.class);
                if (null != customerInfo) {
                    reportDetails.setCustomerInfo(customerInfo);
                }
                if (null != accountInfo) {
                    reportDetails.getAccountInfo().add(accountInfo);

                }
            }
            if ((newExchange != null) || (newExchange.getIn() != null)) {
                CustomerInfo customerInfo = newExchange.getIn().getBody(CustomerInfo.class);
                AccountInfo accountInfo = newExchange.getIn().getBody(AccountInfo.class);
                if (null != accountInfo) {
                    reportDetails.getAccountInfo().add(accountInfo);
                }
                if (null != customerInfo) {
                    reportDetails.setCustomerInfo(customerInfo);
                }
            }
            newExchange.getOut().setBody(reportDetails);
        }
        return newExchange;
    }
}
