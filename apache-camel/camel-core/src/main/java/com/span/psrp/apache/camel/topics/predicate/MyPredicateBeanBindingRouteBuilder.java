package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.builder.RouteBuilder;

public class MyPredicateBeanBindingRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        MyPredicateBeanBinding predicate = new MyPredicateBeanBinding();
        from("direct:start")
            .filter().method(predicate, "isBoston")
                .to("direct:boston");
        from("direct:boston").process(new LogProcessor()); 
        from("direct:newyork").process(new LogProcessor()); 
    }
}
