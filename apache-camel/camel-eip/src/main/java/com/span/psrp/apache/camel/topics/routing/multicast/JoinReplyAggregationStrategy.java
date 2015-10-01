package com.span.psrp.apache.camel.topics.routing.multicast;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class JoinReplyAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange exchange1, Exchange exchange2) {
        if (exchange1 == null) {
            return exchange2;
        } else {
            String body1 = exchange1.getIn().getBody(String.class);
            String body2 = exchange2.getIn().getBody(String.class);
            String merged = (body1 == null) ? body2 : body1 + "," + body2;
            exchange1.getIn().setBody(merged);
            return exchange1;
        }
    }
}
