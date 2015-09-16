package com.span.psrp.apache.camel.topics.transaction.basic.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.span.psrp.apache.camel.topics.transaction.basic.AuditLog;
import com.span.psrp.apache.camel.topics.transaction.basic.DatabaseTransactionRouteBuilder;

public class DatabaseTransactionJavaDSLMain {

	private static ProducerTemplate template;
	public static void main( String[] args ) throws Exception
    {
		DatabaseTransactionRouteBuilder routeBuilder=new DatabaseTransactionRouteBuilder();
		AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("databaseTransaction-context.xml");
		CamelContext ctx = SpringCamelContext.springCamelContext(
				appContext, false);
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		 template = ctx.createProducerTemplate();
 		AuditLog auditLog =new AuditLog();
		auditLog.setMessage("This canmel example");
	    template.sendBody("direct:transacted", auditLog);
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
        
}
