package com.span.psrp.apache.camel.topics.routing.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyCompletionStrategy implements AggregationStrategy, Predicate{
	
	  @Override
      public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		  System.out.print("aggregte method is calling:"+"\n");
          if (oldExchange == null) {
              return newExchange;
          }

          String oldBody = oldExchange.getIn().getBody(String.class);
          String newBody = newExchange.getIn().getBody(String.class);
          oldExchange.getIn().setBody(oldBody + "+" + newBody);
          return oldExchange;
      }

      @Override
      public boolean matches(Exchange exchange) {
    	  System.out.print("maches method is calling:"+"\n");
          return exchange.getIn().getBody(String.class).length() >= 5;
      }
  }



