package com.span.psrp.apache.camel.topics.transformation.differentwaysofrouting.messagefilter.predicate;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultProducerTemplate;

 
public class PredicateFilter {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:start")
                            .filter(new Predicate() {

                                public boolean matches(Exchange exchange) {
                                    final String body = exchange.getIn().getBody(String.class);
                                    return ((body != null) && body.startsWith("Camel"));
                                }})
                            .to("file:src/data/outbox").process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            System.out.println(exchange);
                        }
                    })
                    ;
                }});
            camelContext.start();
            ProducerTemplate template = new DefaultProducerTemplate(camelContext);
            template.start();
            template.sendBody("direct:start", "Camel Multicast");
            template.sendBody("direct:start", "Camel Components");
            template.sendBody("direct:start", "Spring Integration");
            Thread.sleep(5000);
        } finally {
            camelContext.stop();
        }
    }

}