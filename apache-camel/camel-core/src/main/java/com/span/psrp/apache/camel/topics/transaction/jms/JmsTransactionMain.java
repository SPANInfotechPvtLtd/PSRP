package com.span.psrp.apache.camel.topics.transaction.jms;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsTransactionMain {

	private static ProducerTemplate template;
	private static ConsumerTemplate consumerTemplate;
	public static void main( String[] args ) throws Exception{
    	AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("jmsTransaction-context.xml");
    	CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
    	template = camelContext.createProducerTemplate();
		System.out.println("Start camel context");
		String message = "ping";
		//AuditLog auditLog =new AuditLog();
		//auditLog.setMessage("jagan");
	    template.sendBody("jms:inbound", message);
	    consumerTemplate=camelContext.createConsumerTemplate();
	    String dead= consumerTemplate.receiveBody("jms:dead", String.class);
	    System.out.println("***********"+dead);
    	appContext.start();
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Entered>>>>>");
    	appContext.stop();
    }
}
