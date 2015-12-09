package com.span.psrp.reportingsystem.file;

import org.apache.camel.builder.RouteBuilder;

public class FirstRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("Copying the file from target to desktop");
        from("file:target/report-sample?noop=true")
                .to("file:C:/Users/mohanarao_sv/Desktop/report-system");
    }
}