package com.span.psrp.apache.camel.topics.error.retry.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.error.retry.RetryRouteBuilder;

public class RetryRouteMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		RetryRouteBuilder routeBuilder=new RetryRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		String response=null;
 		RetryRouteMain app=new RetryRouteMain();
 		app.template = ctx.createProducerTemplate();
 		app.template.sendBody("direct:start", "Foo");
 		app.template.sendBody("direct:routeSpecific", "Foo");
 		app.template.sendBody("direct:routeSpecificDelay", "Foo");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
