package com.span.psrp.apache.camel.topics.routing.loadbalancer;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelCustomLoadBalanceExample {
	public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:start")
                            .loadBalance(new MyCustomLoadBalancer())
                            .to("direct:a")
                            .to("direct:b");

                    from("direct:a")
                    		 .log("A received: ${body}");

                    from("direct:b")
                    		.log("B received: ${body}");
                }
            });
            
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            
            template.sendBodyAndHeader("direct:start", "Hello", "type", "silver");
            template.sendBodyAndHeader("direct:start", "Camel rocks", "type", "gold");
            template.sendBodyAndHeader("direct:start", "Cool", "type", "gold");
            template.sendBodyAndHeader("direct:start", "Bye", "type", "bronze");
        
        } finally {
            camelContext.stop();
        }
    }
}
