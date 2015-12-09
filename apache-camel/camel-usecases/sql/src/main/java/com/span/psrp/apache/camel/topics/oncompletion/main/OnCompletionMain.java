package com.span.psrp.apache.camel.topics.oncompletion.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.oncompletion.OnCompletionRouteBuilder;

public class OnCompletionMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		OnCompletionRouteBuilder routeBuilder=new OnCompletionRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		OnCompletionMain app=new OnCompletionMain();
 		app.template = ctx.createProducerTemplate();
 		//app.template.requestBody("direct:onCompletion", "this message should be fine");
 		app.template.asyncSendBody("direct:onCompletionFailure", "this message should explode");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
