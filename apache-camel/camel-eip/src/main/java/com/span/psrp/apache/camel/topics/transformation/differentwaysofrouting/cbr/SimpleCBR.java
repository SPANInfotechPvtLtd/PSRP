package com.span.psrp.apache.camel.topics.transformation.differentwaysofrouting.cbr;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
 
public class SimpleCBR {

    public static void main(String[] a) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("file:src/data/inbox?noop=true")
                        .choice()
                        .when(header("CamelFileName").endsWith(".xml"))
                        .to("file:src/data/xmlfiles")
                        .when(header("CamelFileName").endsWith(".csv"))
                        .to("file:src/data/csvfiles")
                        .otherwise()
                        .to("file:src/data/outbox");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();


    }


}