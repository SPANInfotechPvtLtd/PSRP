
package com.span.psrp.apache.camel.topics.processor;

import org.apache.camel.Exchange;

public class MyPredicate implements org.apache.camel.Predicate {
	public boolean matches(Exchange exchange) {
		 return ((exchange != null) && exchange.getIn().getBody(String.class).contains("Hello"));
	}


}
