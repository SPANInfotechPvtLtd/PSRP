package com.span.psrp.apache.camel.topics.error.dlc.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.span.psrp.apache.camel.topics.error.dlc.DlcRouteBuilder;

public class DeadLetterChannelMain {
	ProducerTemplate template;
	public static void main( String[] args )
    {
		DlcRouteBuilder routeBuilder=new DlcRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(1000);
 		DeadLetterChannelMain app=new DeadLetterChannelMain();
 		app.template = ctx.createProducerTemplate();

 		//app.template.sendBodyAndHeader("direct:start", "KaBoom", "myHeader", "original");
 		app.template.sendBodyAndHeader("seda:flakyrouteOriginal", "Foo", "myHeader", "flaky");
 		//app.template.sendBodyAndHeader("direct:start", "Foo", "myHeader", "original");
 		//app.template.sendBodyAndHeader("direct:start", "KaBoom", "myHeader", "original");
 		//
        //app.template.sendBodyAndHeader("direct:multiroute", "KaBoom", "myHeader", "original");
        //app.template.sendBodyAndHeader("direct:multirouteOriginal", "Foo", "myHeader", "original");
        //app.template.sendBodyAndHeader("direct:multirouteOriginal", "KaBoom", "myHeader", "original");
        //app.template.sendBodyAndHeader("direct:useOriginal", "Foo", "myHeader", "original");
        //app.template.sendBodyAndHeader("direct:useOriginal", "KaBoom", "myHeader", "original");
 		ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
