package com.span.psrp.apache.camel.topics.expressions; 

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
 
public class HeaderExpression {
    public static void main(String[] a) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("file:src/data/inbox/dini?noop=true")
                        .choice()
                        .when(header("CamelFileName").endsWith(".txt"))
                        .to("file:src/data/outbox")
                        .otherwise()
                        .to("file:src/data/others");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}
