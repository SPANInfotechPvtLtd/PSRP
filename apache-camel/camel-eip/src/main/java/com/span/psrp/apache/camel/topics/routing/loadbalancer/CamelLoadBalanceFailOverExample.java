package com.span.psrp.apache.camel.topics.routing.loadbalancer;

import java.io.IOException;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelLoadBalanceFailOverExample {
	public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:start")
                            .loadBalance()
                            .failover()
                            //.failover(IllegalArgumentException.class)
                            .to("direct:a")
                            .to("direct:b");

                    from("direct:a")
                            .choice()
                            	.when(body().contains("Twooo"))
                            		.throwException(new IllegalArgumentException("Pass the correct argument!"))
                                	//.throwException(new IOException("Pass the correct argument!"))
                                .end()
                             .end()
                    		 .log("A received: ${body}");

                    from("direct:b")
                    		.log("B received: ${body}");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBody("direct:start", "One");
            template.sendBody("direct:start", "Twooo");
            template.sendBody("direct:start", "Three");
            template.sendBody("direct:start", "Four");
        } finally {
            camelContext.stop();
        }
    }
}
