package com.span.psrp.apache.camel.topics.agreegate;

import java.util.HashSet;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class SetAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String body = newExchange.getIn().getBody(String.class);
        if (oldExchange == null) {
            Set<String> set = new HashSet<String>();
            set.add(body);
            newExchange.getIn().setBody(set);
            System.out.print("newExchange count"+"\n");
            return newExchange;
        } else {
            Set<String> set = oldExchange.getIn().getBody(Set.class);
            set.add(body);
            System.out.print("oldExchange count"+"\n");
            return oldExchange;
        }
    }
}
