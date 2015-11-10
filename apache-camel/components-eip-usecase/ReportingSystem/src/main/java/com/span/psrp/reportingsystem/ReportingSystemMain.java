package com.span.psrp.reportingsystem;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportingSystemMain {

	private static ProducerTemplate template;
	private static ConsumerTemplate consumerTemplate;
	
	public static void main( String[] args ) throws Exception{
    	AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("reporting-system-context.xml");
    	CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
    	template = camelContext.createProducerTemplate();
		System.out.println("Start camel context");
	    template.sendBody("direct:transacted", "");
	    consumerTemplate=camelContext.createConsumerTemplate();
	    String consumerTemplateResponse= consumerTemplate.receiveBody("direct:transacted", String.class);
    	appContext.start();
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Entered>>>>>");
    	appContext.stop();
    }
}
