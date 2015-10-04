package com.span.psrp.apache.camel.topics.routing.splitter;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

/**
 * Created by mukesh.k on 9/29/2015.
 */
public class CamelSplitterStopOnExceptionExample {
    public static final void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("processOrderItem1", new OrderItemProcessor());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    onException(Exception.class)
                            .handled(true)
                            .to("direct:onException")
                            .log("Exception thrown. Stop routing..");

                    from("timer://generateOrders456?fixedRate=true&period=10000")
                            .log("Generate order items")
                            .process(new Processor() {
                                public void process(Exchange exchange) throws Exception {
                                    Order order = new Order(1);
                                    order.addItem("Coke", 20, 2);
                                    order.addItem("Banana", 12, 1);
                                    order.addItem("Apple", 5, 250);
                                    exchange.getIn().setBody(order);
                                }
                            })
                            .to("direct:processOrder123");

                    from("direct:processOrder123")
                            .log("Process order ${body}")
                            .split(simple("${body.items}")).stopOnException()
                            .to("direct:processOrderItem1")
                            .log("Processing done ${body}")
                            .process(new MyProcessor())
                            .end()
                            .log("Order processed: ${body}");
    
                    from("direct:processOrderItem1")
                            .log("Processing item ${body.itemName}")
                            .to("bean:processOrderItem1");

                }
            });
            camelContext.start();
            Thread.sleep(5000);
        } finally {
            camelContext.stop();
        }
    }
}
