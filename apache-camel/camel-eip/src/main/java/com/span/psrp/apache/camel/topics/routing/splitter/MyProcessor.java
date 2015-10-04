package com.span.psrp.apache.camel.topics.routing.splitter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class MyProcessor implements Processor {
    
    @Override
    public void process(Exchange exchange) throws Exception {
       Order order = new Order(2);
       order.addItem("Bread", 10, Integer.parseInt("abc"));
       exchange.getIn().setBody(order);
        
    }
}
