package com.span.psrp.apache.camel.topics.transaction.policy.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.span.psrp.apache.camel.topics.transaction.basic.AuditLog;

public class TransactionPolicyMain {
	
private static ProducerTemplate template;
	
	public static void main( String[] args ) throws Exception{
    	AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("transactionPolicy-context.xml");
    	CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
    	template = camelContext.createProducerTemplate();
		System.out.println("Start camel context");
		//String message = "ping";
		AuditLog auditLog =new AuditLog();
		auditLog.setMessage("jagan");
	    template.sendBody("direct:policies", auditLog);
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
