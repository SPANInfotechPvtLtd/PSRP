package com.span.psrp.apache.camel.topics.propertieswithcustomexpression; 

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
 
public class SampleCamel {

    private static String PACKAGE_NAME = SampleCamel.class.getPackage().getName();
    private static CustomExpression CUST_EXP = new CustomExpression();
    private static String ORIGINAI_REQUEST_PROPERTY = PACKAGE_NAME +"ORIGINAI_REQUEST_PROPERTY";
    public static void main(String[] args) throws  Exception{

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:src/data/inbox?noop=true").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty(ORIGINAI_REQUEST_PROPERTY, CUST_EXP.evaluate(exchange, Integer.class));
                    }
                })

                        .to("file:src/data/outbox").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Integer myint = (Integer)exchange.getProperty(ORIGINAI_REQUEST_PROPERTY);
                        System.out.println(myint);
                    }
                });
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}
