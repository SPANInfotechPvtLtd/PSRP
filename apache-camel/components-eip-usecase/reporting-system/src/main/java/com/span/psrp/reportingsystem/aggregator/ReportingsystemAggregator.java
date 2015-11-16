package com.span.psrp.reportingsystem.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.span.psrp.reportingsystem.model.AccountInfo;
import com.span.psrp.reportingsystem.model.CustomerInfo;

public class ReportingsystemAggregator implements AggregationStrategy {
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		if (null != oldExchange && null != newExchange) {
			if (oldExchange != null || oldExchange.getIn() != null) {
				CustomerInfo customerInfo = oldExchange.getIn().getBody(CustomerInfo.class);
				AccountInfo accountInfo = oldExchange.getIn().getBody(AccountInfo.class);
				if(null!=customerInfo){
				System.out.print("**********" +customerInfo.getAddressLine1());
				}
				if(null!=accountInfo){
					System.out.print("**********" + accountInfo.getAccountNo());
				}
			}
			if (newExchange != null || newExchange.getIn() != null) {
				CustomerInfo customerInfo = newExchange.getIn().getBody(CustomerInfo.class);
				AccountInfo accountInfo = newExchange.getIn().getBody(AccountInfo.class);
				if(null!=accountInfo){
					System.out.print("**********" + accountInfo.getAccountNo());
				}
				if(null!=customerInfo){
					System.out.print("**********" +customerInfo.getAddressLine1());
			      }
				
			}
		}
		return newExchange;
	}
}
