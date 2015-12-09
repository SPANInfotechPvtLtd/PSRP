

package com.span.psrp.apache.camel.topics.error.retry;

import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.error.util.SporadicProcessor;

public class RetryRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       errorHandler(defaultErrorHandler().maximumRedeliveries(2));

        from("direct:start")
            .bean(SporadicProcessor.class)
            .to("mock:result");

        from("direct:routeSpecific")
            .errorHandler(defaultErrorHandler().maximumRedeliveries(2))
            .bean(SporadicProcessor.class)
            .to("mock:result");

        from("direct:routeSpecificDelay")
            .errorHandler(defaultErrorHandler()
            .maximumRedeliveries(2).redeliveryDelay(500))
            .bean(SporadicProcessor.class)
            .to("mock:result");
    }
}
