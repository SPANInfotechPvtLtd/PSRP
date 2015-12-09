package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class PredicateMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		final String boston = "<someXml><city>Boston</city></someXml>";
		final String newyork = "<someXml><city>NewYork</city></someXml>";
		MyPredicateBeanBindingRouteBuilder routeBuilder=new MyPredicateBeanBindingRouteBuilder();
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
