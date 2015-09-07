package com.span.psrp.apache.camel.topics.typeconverter.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.typeconverter.MyPersonGreeter;
import com.span.psrp.apache.camel.topics.typeconverter.TypeConverterRouteBuilder;



public class MyPersonTypeConverterMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		TypeConverterRouteBuilder routeBuilder=new TypeConverterRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(2000);
 		MyPersonTypeConverterMain app=new MyPersonTypeConverterMain();
 		app.template = ctx.createProducerTemplate();
 		app.template.requestBody("direct:start", "Scott|Cranton",MyPersonGreeter.class);
 		app.template.requestBody("direct:start", "Invalid formatted string");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
