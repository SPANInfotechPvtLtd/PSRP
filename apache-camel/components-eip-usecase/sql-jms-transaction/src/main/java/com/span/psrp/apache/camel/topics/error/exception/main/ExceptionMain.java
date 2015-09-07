package com.span.psrp.apache.camel.topics.error.exception.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.error.exception.ExceptionRouteBuilder;

public class ExceptionMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		ExceptionRouteBuilder routeBuilder=new ExceptionRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		String response=null;
 		ExceptionMain app=new ExceptionMain();
 		app.template = ctx.createProducerTemplate();
 		//app.template.sendBody("direct:start", "KaBoom");
 		//app.template.sendBody("direct:handled", "KaBoom");
 		app.template.sendBody("direct:start", "Foo");
 		//app.template.sendBody("direct:start", "KaBoom");
 		System.out.print("************* ::"+response);
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
