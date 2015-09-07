package com.span.psrp.apache.camel.topics.transformation.enrich;

import org.apache.camel.builder.RouteBuilder;

public class EnrichRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .enrich("bean:myExpander?method=expand");
    }

}
