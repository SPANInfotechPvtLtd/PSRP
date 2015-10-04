package com.span.psrp.apache.camel.topics.routing.aggregator.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.routing.aggregator.AggregationRouteBuilder;

public class AggregateAggregationStrategyIsPredicateMain {

		public static ProducerTemplate producerTemplate;
		public static  CamelContext camelContext;

	
	public static void main(String args[]) throws Exception{
		camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new AggregationRouteBuilder());
		camelContext.start();
 		Thread.sleep(2000);
 		producerTemplate =camelContext.createProducerTemplate();
		        //producerTemplate.sendBodyAndHeader("direct:start", "A", "id", 123);
		       /* producerTemplate.sendBodyAndHeader("direct:start", "B", "id", 123);
		        producerTemplate.sendBodyAndHeader("direct:start", "C", "id", 123);
		        producerTemplate.sendBodyAndHeader("direct:start", "X", "id", 123);
		        producerTemplate.sendBodyAndHeader("direct:start", "Y", "id", 123);*/
		        producerTemplate.sendBodyAndHeader("direct:start", "ZZZZAAA", "id", 123456);
	}

	    
}	    
	        



	      
