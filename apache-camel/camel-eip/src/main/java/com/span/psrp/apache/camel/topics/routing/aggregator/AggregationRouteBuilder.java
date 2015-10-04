package com.span.psrp.apache.camel.topics.routing.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class AggregationRouteBuilder extends RouteBuilder{

	 @Override
     public void configure() throws Exception {
		from("direct:start")
		   .aggregate(header("id"), new MyCompletionStrategy())
		                    .to("direct:aggregated");
		from("direct:aggregated")
		   .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				 System.out.print("process method is calling:"+"\n");
				
			}
		})
		                    .to("mock:aggregated");
		            }
        
        
    } 


