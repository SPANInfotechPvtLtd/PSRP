package com.span.psrp.apache.camel.topics.logging.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.logging.LoggingRouteBuilder;

public class LoggingMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		LoggingRouteBuilder routeBuilder=new LoggingRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		LoggingMain app=new LoggingMain();
 		app.template = ctx.createProducerTemplate();
 		//app.template.sendBody("direct:start", "Foo");
 		//app.template.sendBody("direct:start", newyork);
 		app.template.sendBody("direct:routeSpecific", "KaBoom");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
