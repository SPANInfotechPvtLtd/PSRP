package com.span.psrp.apache.camel.topics.routing.aggregator.main;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.routing.aggregator.AggregateClosedCorrelationKeyRouteBuilder;

public class AggregateClosedCorrelationKeyMain {

	public static ProducerTemplate producerTemplate;
	public static  CamelContext camelContext;


public static void main(String args[]) throws Exception{
	camelContext = new DefaultCamelContext();
	camelContext.addRoutes(new AggregateClosedCorrelationKeyRouteBuilder());
	camelContext.start();
		Thread.sleep(2000);
		producerTemplate =camelContext.createProducerTemplate();
		// #Scenario:1
		/*producerTemplate.sendBodyAndHeader("direct:start", "A", "id", 1);
		producerTemplate.sendBodyAndHeader("direct:start", "B", "id", 1);
        // should be closed
        try {
        	producerTemplate.sendBodyAndHeader("direct:start", "C", "id", 1);
          } catch (CamelExecutionException e) {
           System.out.print("****"+e.getMessage());
        }*/

     // #Scenario:2
		
		producerTemplate.sendBodyAndHeader("direct:start", "A", "id", 1);
        producerTemplate.sendBodyAndHeader("direct:start", "B", "id", 1);
        producerTemplate.sendBodyAndHeader("direct:start", "C", "id", 2);
        producerTemplate.sendBodyAndHeader("direct:start", "D", "id", 2);
        producerTemplate.sendBodyAndHeader("direct:start", "E", "id", 3);
        producerTemplate.sendBodyAndHeader("direct:start", "F", "id", 3);
        // should NOT be closed because only 2 and 3 is remembered as they are the two last used
        producerTemplate.sendBodyAndHeader("direct:start", "G", "id", 1);

        // should be closed
        try {
            producerTemplate.sendBodyAndHeader("direct:start", "H", "id", 2);
        } catch (CamelExecutionException e) {
        	System.out.print("****"+e.getMessage());
        }

	       
}
}
