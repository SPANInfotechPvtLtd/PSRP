package com.span.psrp.reportingsystem.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class ReportingsystemAggregator implements AggregationStrategy{
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	      
	        return newExchange;
	    }

	

}
