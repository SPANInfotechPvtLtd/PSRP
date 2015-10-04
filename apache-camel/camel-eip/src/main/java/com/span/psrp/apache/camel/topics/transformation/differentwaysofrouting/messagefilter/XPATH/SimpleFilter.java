package com.span.psrp.apache.camel.topics.transformation.differentwaysofrouting.messagefilter.XPATH;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

 
public class SimpleFilter {

    public static void main(String[] a)throws Exception{
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("file:src/data/inbox?noop=true")
                       .filter(xpath("/order[@type = 'GOLD']"))
                        .to("file:src/data/goldclass")
                        .filter(xpath("/order[@type = 'COPER']"))
                        .to("file:src/data/copperclass");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();

    }

}
