package com.span.psrp.apache.camel.topics.routing.multicast;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class NumberValidateProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Object body = exchange.getIn().getBody();
        if (body instanceof Number) {
            return;
        }
        String bodyAsString = body.toString();
        Integer.parseInt(bodyAsString);
    }
}
