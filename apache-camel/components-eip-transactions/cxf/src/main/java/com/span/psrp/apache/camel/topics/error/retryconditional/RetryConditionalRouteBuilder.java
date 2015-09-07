package com.span.psrp.apache.camel.topics.error.retryconditional;

import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.error.util.SporadicProcessor;

public class RetryConditionalRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                .retryWhile(simple("${header.CamelRedeliveryCounter} < 2 or ${date:now:EEE} contains 'Tue'")));
        from("direct:start")
            .bean(SporadicProcessor.class)
            .to("mock:result");
    }
}
