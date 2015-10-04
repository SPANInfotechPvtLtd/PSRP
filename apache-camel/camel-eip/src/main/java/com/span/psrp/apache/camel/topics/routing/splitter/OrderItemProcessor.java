package com.span.psrp.apache.camel.topics.routing.splitter;

/**
 * Created by mukesh.k on 9/29/2015.
 */
public class OrderItemProcessor {
    public void process(OrderItem item) {
    	item.process();
        System.out.print(item+"\n");
    }
}
