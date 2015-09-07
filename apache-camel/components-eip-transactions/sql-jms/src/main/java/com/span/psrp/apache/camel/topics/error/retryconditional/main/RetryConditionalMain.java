package com.span.psrp.apache.camel.topics.error.retryconditional.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.error.retryconditional.RetryConditionalRouteBuilder;

public class RetryConditionalMain {
	ProducerTemplate template;
	public static void main( String[] args ){
		RetryConditionalRouteBuilder routeBuilder=new RetryConditionalRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		RetryConditionalMain app=new RetryConditionalMain();
 		app.template = ctx.createProducerTemplate();
 		app.template.sendBody("direct:start", "Foo");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
