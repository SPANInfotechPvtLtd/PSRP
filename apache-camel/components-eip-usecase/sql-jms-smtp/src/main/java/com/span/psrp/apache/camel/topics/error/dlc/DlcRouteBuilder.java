

package com.span.psrp.apache.camel.topics.error.dlc;

import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.error.util.FlakyProcessor;
import com.span.psrp.apache.camel.topics.predicate.LogProcessor;

public class DlcRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel("seda:error"));

        from("direct:start")
                .routeId("myDlcRoute")
            .setHeader("myHeader").constant("changed")
            .bean(FlakyProcessor.class)
            .to("mock:result");

        from("direct:multiroute")
                .routeId("myDlcMultistepRoute")
            .setHeader("myHeader").constant("multistep")
            .inOut("seda:flakyroute")
            .setHeader("myHeader").constant("changed")
            .to("mock:result");

        from("seda:flakyroute")
                .routeId("myFlakyRoute")
            .setHeader("myHeader").constant("flaky")
            .bean(FlakyProcessor.class);

        from("direct:multirouteOriginal")
                .routeId("myDlcMultistepOriginalRoute")
            .setHeader("myHeader").constant("multistep")
            .inOut("seda:flakyrouteOriginal")
            .setHeader("myHeader").constant("changed")
            .to("mock:result");

        from("seda:flakyrouteOriginal")
                .routeId("myFlakyRouteOriginal")
                .errorHandler(deadLetterChannel("seda:error").useOriginalMessage())
            .setHeader("myHeader").constant("flaky")
            .bean(FlakyProcessor.class);

        from("direct:routeSpecific")
                .routeId("myDlcSpecificRoute")
                .errorHandler(deadLetterChannel("seda:error"))
            .bean(FlakyProcessor.class)
            .to("mock:result");

        from("direct:useOriginal")
                .routeId("myDlcOriginalRoute")
                .errorHandler(deadLetterChannel("seda:error").useOriginalMessage())
            .setHeader("myHeader").constant("changed")
            .bean(FlakyProcessor.class)
            .to("mock:result");

        from("seda:error")
                .routeId("myDlcChannelRoute").log("seda:error")
            .to("log:dlc?showAll=true&multiline=true")
            .to("mock:error");
    }
}
