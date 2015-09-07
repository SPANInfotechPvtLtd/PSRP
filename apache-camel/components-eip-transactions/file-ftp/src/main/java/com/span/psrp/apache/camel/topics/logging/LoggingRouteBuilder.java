package com.span.psrp.apache.camel.topics.logging;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.error.util.FlakyProcessor;

public class LoggingRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        errorHandler(loggingErrorHandler()
            .logName("MyLoggingErrorHandler")
            .level(LoggingLevel.ERROR)
        );
        from("direct:start").bean(FlakyProcessor.class).to("mock:result");

        from("direct:routeSpecific")
            .errorHandler(loggingErrorHandler()
                .logName("MyRouteSpecificLogging")
                .level(LoggingLevel.ERROR))
            .bean(FlakyProcessor.class)
            .to("mock:result");
    }
}
