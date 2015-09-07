package com.span.psrp.apache.camel.topics.typeconverter;

import org.apache.camel.builder.RouteBuilder;

public class TypeConverterRouteBuilder extends RouteBuilder{
            @Override
            public void configure() throws Exception {
                onException(IllegalArgumentException.class)
                    .handled(true)
                    .to("mock:error")
                    .transform(constant("Malformed Person String"));

                from("direct:start")
                    .bean(MyPersonGreeter.class,"sayHello")
                    .to("mock:person");
            }
}
