package com.span.psrp.apache.camel.topics.transformation.enrich.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import com.span.psrp.apache.camel.topics.transformation.enrich.EnrichWithAggregatorRouteBuilder;
import com.span.psrp.apache.camel.topics.transformation.enrich.MergeInReplacementText;

public class EnrichWithAggregatorMain {

	ProducerTemplate template;
	public static void main( String[] args ) throws Exception
    {
		EnrichWithAggregatorRouteBuilder routeBuilder=new EnrichWithAggregatorRouteBuilder();
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("myMerger", new MergeInReplacementText());
		CamelContext ctx=new DefaultCamelContext(jndiContext);
        try {
        routeBuilder.setMyMerger(ctx.getRegistry().lookupByNameAndType("myMerger", MergeInReplacementText.class));
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(2000);
 		EnrichWithAggregatorMain app=new EnrichWithAggregatorMain();
 		app.template = ctx.createProducerTemplate();
 		 String response = app.template.requestBody("direct:start", "Hello MA", String.class);
        System.out.print("*****************MA*****************"+response+"\n");
        response = app.template.requestBody("direct:start", "I like CA", String.class);
        System.out.print("*****************CA*****************"+response+"\n");
        ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
