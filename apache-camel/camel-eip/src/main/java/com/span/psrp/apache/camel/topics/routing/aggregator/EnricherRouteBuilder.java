package com.span.psrp.apache.camel.topics.routing.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class EnricherRouteBuilder extends RouteBuilder {

	@Override
    public void configure() throws Exception {
        from("direct:start")
            .enrich("direct:enrich", new AggregationStrategy() {
                public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                    if (oldExchange == null) {
                        return newExchange;
                    }
                    // should always be in
                    String body = newExchange.getIn().getBody(String.class);
                    return newExchange;
                }
            })
            .to("direct:extend")
            .end()
            .to("direct:result");

        from("direct:enrich").pipeline("direct:loga", "direct:logb")
        .to("direct:logfoo")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                     String s = exchange.getIn().getBody(String.class);
                     System.out.println("direct:enrich:"+s+"\n");    
                    exchange.getIn().setBody("Hi " + s);
                    
                }
            });
        from("direct:extend")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                 String s = exchange.getIn().getBody(String.class);
                 System.out.println("direct:extend:"+s+"\n");            }
        });
        from("direct:result")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                 String s = exchange.getIn().getBody(String.class);
                System.out.println("direct:result:"+s+"\n");
            }
        });
        
        from("direct:loga")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                 String s = exchange.getIn().getBody(String.class);
                 System.out.println("direct:loga:"+s+"\n");            }
        });
        from("direct:logb")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                 String s = exchange.getIn().getBody(String.class);
                System.out.println("direct:logb:"+s+"\n");
            }
        });
        from("direct:logfoo")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                 String s = exchange.getIn().getBody(String.class);
                System.out.println("direct:logfoo:"+s+"\n");
            }
        });
        
}
    }
	
	


