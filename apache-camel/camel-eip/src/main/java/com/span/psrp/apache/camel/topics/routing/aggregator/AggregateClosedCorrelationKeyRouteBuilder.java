package com.span.psrp.apache.camel.topics.routing.aggregator;

import org.apache.camel.builder.RouteBuilder;

public class AggregateClosedCorrelationKeyRouteBuilder extends RouteBuilder{

	// #scenario :1 completion size :2 closeCorrelationKeyOnCompletion:100
	// #scenario :2 completion size :2 closeCorrelationKeyOnCompletion :2
	
	 @Override
     public void configure() throws Exception {
         from("direct:start")
                 .aggregate(header("id"), new BodyInAggregatingStrategy())
                 .completionSize(2).closeCorrelationKeyOnCompletion(2)
                 .to("mock:result");
     }
	 
	 
 }       
       
   

