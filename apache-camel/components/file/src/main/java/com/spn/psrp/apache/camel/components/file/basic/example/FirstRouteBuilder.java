package com.span.psrp.apache.camel.components.file.basic.example;

import org.apache.camel.builder.RouteBuilder;

public class FirstRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
	from("file:D:/camel/input?noop=true")
	.process(new LogProcessor())
	.bean(new Transormer(),"transformContent")
	.to("file:D:/camel/output");	
	}

}
