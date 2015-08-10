package com.spn.psrp.apache.camel.topics.predicate.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.spn.psrp.apache.camel.topics.predicate.MyPredicateInlineRouteBuilder;

public class PredicateInlineRouteBuilderMain {
	
	ProducerTemplate template;
	public static void main( String[] args )
    {
		final String boston = "<someXml><city>Boston</city></someXml>";
		final String newyork = "<someXml><city>NewYork</city></someXml>";
		MyPredicateInlineRouteBuilder routeBuilder=new MyPredicateInlineRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		PredicateMain app=new PredicateMain();
 		app.template = ctx.createProducerTemplate();
 		app.template.sendBody("direct:start", boston);
 		//app.template.sendBody("direct:start", newyork);
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
