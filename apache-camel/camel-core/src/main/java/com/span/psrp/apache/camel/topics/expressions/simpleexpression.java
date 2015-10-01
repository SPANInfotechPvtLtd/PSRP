package com.span.psrp.apache.camel.topics.expressions;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

 

 
public class SimpleExpression {
     public static void main(String[] a) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
           camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("file:src/data/inbox/dini?noop=true")
                        .choice()
                        .when(simple("${body} contains 'Dinesh'"))
                        .to("file:src/data/outbox")
                        .otherwise()
                        .to("file:src/data/others");
            }
        });
         camelContext.start();
        Thread.sleep(10000);
         camelContext.stop();
    }
}
