

package com.span.psrp.apache.camel.topics.error.dotry.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.error.dotry.DoTryRouteBuilder;


public class DoTryMain{
	ProducerTemplate template;
	public static void main( String[] args )
    {
		DoTryRouteBuilder routeBuilder=new DoTryRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(2000);
 		DoTryMain app=new DoTryMain();
 		app.template = ctx.createProducerTemplate();
 		String response = null;
        //response = app.template.requestBody("direct:start", "Kaboom", String.class);
        //response = app.template.requestBody("direct:unhandled", "Kaboom", String.class);
 		response = app.template.requestBodyAndHeader("direct:conditional", "Kaboom", "jedi", "This isn't the Exception you are looking for...", String.class);
        System.out.print("**************"+"\n"+response);
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
