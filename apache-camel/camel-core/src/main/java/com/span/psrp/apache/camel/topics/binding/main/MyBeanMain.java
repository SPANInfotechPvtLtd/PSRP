package com.span.psrp.apache.camel.topics.binding.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.binding.MyBeanRouteBuilder;

public class MyBeanMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		
		MyBeanRouteBuilder routeBuilder=new MyBeanRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		MyBeanMain app=new MyBeanMain();
 		app.template = ctx.createProducerTemplate();
 		 final String response1 = app.template.requestBody("direct:normal", "Scott", String.class);
 		 System.out.print(response1 +"\n");
 		final String response2 = app.template.requestBody("direct:hipster", "Scott", String.class);
 		System.out.print(response2+"\n");
 		String response3 = app.template.requestBodyAndHeader("direct:undecided", "Scott", "hipster", true, String.class);
 		System.out.print(response3+"\n");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
