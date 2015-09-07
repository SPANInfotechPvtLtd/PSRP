package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class LogProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		System.out.println("predicate processer "+ "\n");
		System.out.println("getIn "+exchange.getIn()+"\n");
		
	}

}
