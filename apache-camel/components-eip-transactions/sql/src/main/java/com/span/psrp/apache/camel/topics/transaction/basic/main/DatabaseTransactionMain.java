package com.span.psrp.apache.camel.topics.transaction.basic.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.span.psrp.apache.camel.topics.transaction.basic.AuditLog;

public class DatabaseTransactionMain {
	
	private static ProducerTemplate template;
	public static void main( String[] args ) throws Exception
    {
    	AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("databaseTransaction-context.xml");
    	CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
    	template = camelContext.createProducerTemplate();
		System.out.println("Start camel context");
		AuditLog auditLog =new AuditLog();
		auditLog.setMessage("This canmel example");
	    template.sendBody("direct:transacted", auditLog);
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
