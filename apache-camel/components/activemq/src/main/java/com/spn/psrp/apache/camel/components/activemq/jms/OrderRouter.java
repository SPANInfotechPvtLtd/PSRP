
package com.spn.psrp.apache.camel.components.activemq.jms;

import org.apache.camel.builder.RouteBuilder;

public class OrderRouter extends RouteBuilder {

    @Override
    public void configure() {
        // load file orders from src/data into the JMS queue
        from("file:src/data?noop=true").to("jms:incomingOrders");
        // content-based router
        from("jms:incomingOrders")
        .choice()
            .when(header("CamelFileName").endsWith(".xml"))
                .to("jms:topic:xmlOrders")
            .when(header("CamelFileName").endsWith(".csv"))
                .to("jms:topic:csvOrders");
        from("jms:topic:xmlOrders").to("jms:accounting");  
        from("jms:topic:xmlOrders").to("jms:production");  
        // test that our route is working
        from("jms:accounting").process(new LogProcessor());  
        from("jms:production").process(new LogProcessor());
    }    
}
    

