package com.span.psrp.apache.camel.topics.routing.loadbalancer;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by mukesh.k on 10/1/2015.
 */
public class CamelLoadBalanceRoundRobinExample {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("timer:myTimer?period=1s")
                            .loadBalance()
                            .roundRobin()
                            .to("direct:a")
                            .to("direct:b")
                            .to("direct:c");

                    from("direct:a")
                    .setBody()
                    .constant("Endpoint Direct a").process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							System.out.print(exchange.getIn().getBody()+"\n");
							
						}
					})
                    .to("mock:out");

            from("direct:b")
                    .setBody()
                    .constant("Endpoint Direct b").process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							System.out.print(exchange.getIn().getBody()+"\n");									
						}
					})
                    .to("mock:out");

            from("direct:c")
                    .setBody()
                    .constant("Endpoint Direct c").process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							System.out.print(exchange.getIn().getBody()+"\n");	
							
						}
					})
                    .to("mock:out");
        }
            });
            camelContext.start();
            Thread.sleep(10000);
        } finally {
            camelContext.stop();
        }
    }
}
