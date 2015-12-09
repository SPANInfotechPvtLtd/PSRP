package com.span.psrp.apache.camel.topics.transformation;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .transform(simple("Hello ${body}"));
    }

}
