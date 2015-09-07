package com.span.psrp.apache.camel.topics.processor;

import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.predicate.LogProcessor;

public class MyProcessorRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
    	MyPredicate myPredicate =new MyPredicate();
        from("direct:start")
            .process(new MyProcessor()).bean(myPredicate)
            .to("direct:hello");
        
        from("direct:hello").process(new LogProcessor()); 
    }
}
