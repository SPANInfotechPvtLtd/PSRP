package com.span.psrp.apache.camel.topics.processor.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.processor.MyProcessorRouteBuilder;

public class MyProcessorMain {

	ProducerTemplate template;
	public static void main( String[] args )
    {
		MyProcessorRouteBuilder routeBuilder=new MyProcessorRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		MyProcessorMain app=new MyProcessorMain();
 		app.template = ctx.createProducerTemplate();
 		app.template.sendBodyAndHeader("direct:start", "Scott", "language", "en");
 		//app.template.sendBodyAndHeader("direct:start", "Scott", "language", "fr");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
