package com.span.psrp.apache.camel.topics.routing.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class BodyInAggregatingStrategy implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newExchange.getIn().getBody(String.class);
        oldExchange.getIn().setBody(oldBody + "+" + newBody);
        System.out.print("Old Exchange + New Exchange :"+oldExchange+"\n");
        return oldExchange;
    }

    /**
     * An expression used to determine if the aggregation is complete
     */
    public boolean isCompleted(@ExchangeProperty(Exchange.AGGREGATED_SIZE) Integer aggregated) {
    	System.out.print("isCompleted method is calling");
        if (aggregated == null) {
            return false;
        }

        return aggregated == 3;
    }

}
