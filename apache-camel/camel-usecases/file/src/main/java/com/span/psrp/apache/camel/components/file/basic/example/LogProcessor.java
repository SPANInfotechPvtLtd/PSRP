package com.span.psrp.apache.camel.components.file.basic.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LogProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		System.out.println("processing "+exchange.getIn().getBody(String.class));
	}

}
