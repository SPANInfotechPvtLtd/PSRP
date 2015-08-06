package com.spn.psrp.apache.camel.components.activemq.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
/**
 * Hello world!
 *
 */
public class App {
	private ConnectionFactory connectionFactory = null;
	public static void main(String[] args) throws Exception {
        // set up the underlying camel context and add the ActiveMQ component
        CamelContext camelContext = new DefaultCamelContext();
        App app =new App();
        app.connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616"); 
        camelContext.addComponent("jms",ActiveMQComponent.jmsComponentAutoAcknowledge(app.connectionFactory));
        // start the context
        camelContext.addRoutes(new OrderRouter());
        camelContext.start();
        Thread.sleep(5*60*1000);
        // send the message
        // stop the context
        camelContext.stop();
    }
}
    
   

