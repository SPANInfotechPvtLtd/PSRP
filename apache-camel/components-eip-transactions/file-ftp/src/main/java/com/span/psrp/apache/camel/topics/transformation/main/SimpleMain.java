package com.span.psrp.apache.camel.topics.transformation.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.transformation.SimpleRouteBuilder;

public class SimpleMain {

	ProducerTemplate template;
	public static void main( String[] args )
    {
		SimpleRouteBuilder routeBuilder=new SimpleRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(2000);
 		SimpleMain app=new SimpleMain();
 		app.template = ctx.createProducerTemplate();
 		String response = app.template.requestBody("direct:start", "Camel Rocks", String.class);
        System.out.println("Hello Camel Rocks"+ response); 		
        ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }}
