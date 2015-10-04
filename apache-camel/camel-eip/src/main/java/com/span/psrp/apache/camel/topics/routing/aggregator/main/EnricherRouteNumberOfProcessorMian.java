package com.span.psrp.apache.camel.topics.routing.aggregator.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.routing.aggregator.EnricherRouteBuilder;

public class EnricherRouteNumberOfProcessorMian {

	public static ProducerTemplate producerTemplate;
	public static  CamelContext camelContext;

public static void main(String args[]) throws Exception{
	camelContext = new DefaultCamelContext();
	camelContext.addRoutes(new EnricherRouteBuilder());
	camelContext.start();
		Thread.sleep(2000);
		producerTemplate =camelContext.createProducerTemplate();
		producerTemplate.requestBodyAndHeader("direct:start", "Claus", "id", 1);
}

	       
}
