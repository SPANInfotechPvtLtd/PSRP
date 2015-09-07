package com.span.psrp.apache.camel.topics.transformation.enrich.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import com.span.psrp.apache.camel.topics.transformation.enrich.AbbreviationExpander;
import com.span.psrp.apache.camel.topics.transformation.enrich.EnrichRouteBuilder;

public class EnrichMain {

	ProducerTemplate template;
	public static void main( String[] args ) throws Exception
    {
		EnrichRouteBuilder routeBuilder=new EnrichRouteBuilder();
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("myExpander", new AbbreviationExpander());
		CamelContext ctx=new DefaultCamelContext(jndiContext);
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(2000);
 		EnrichMain app=new EnrichMain();
 		app.template = ctx.createProducerTemplate();
        String response = app.template.requestBody("direct:start", "MA", String.class);
        System.out.print("*****************MA*****************"+response+"\n");
        response = app.template.requestBody("direct:start", "CA", String.class);
        System.out.print("*****************CA*****************"+response+"\n");
        ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }}

