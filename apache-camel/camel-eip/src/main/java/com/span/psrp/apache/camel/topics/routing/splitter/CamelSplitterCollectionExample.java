package com.span.psrp.apache.camel.topics.routing.splitter;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;
import java.util.List;

public class CamelSplitterCollectionExample {
	public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                	from("direct:start")
                	.to("direct:a")
                    .to("direct:b");
                    
                    from("direct:a")
           		 		.log("A received: ${body}")
           		 		.split(body())
           		 		.log("Split line ${body}");

                    from("direct:b")
                    	.log("B received: ${body}")
                    	.split(body())
                        .log("Split line ${body}");
                }
            });

            List<String> body1 = new ArrayList<String>();
            body1.add("A");
            body1.add("B");
            body1.add("C");

            int body2[] = new int[5];
            body2[0] = 10;
            body2[1] = 20;
            body2[2] = 30;

            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBody("direct:a", body1);
            template.sendBody("direct:b", body2);
            
        } finally {
            camelContext.stop();
        }
    }
}
