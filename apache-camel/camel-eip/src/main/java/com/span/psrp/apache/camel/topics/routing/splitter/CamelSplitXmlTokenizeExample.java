package com.span.psrp.apache.camel.topics.routing.splitter;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by mukesh.k on 9/29/2015.
 */
public class CamelSplitXmlTokenizeExample {
    public static final void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:author")
                            .log("Find all the authors")
                            .split().tokenizeXML("tags").process(new Processor() {
								@Override
								public void process(Exchange exchange) throws Exception {
									System.out.println("******"+exchange.getIn().getBody());
								}
							})
                            .log("${body} split size: ${header.CamelSplitSize}")
                            .end();
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            String filename = "src/main/xsd/resources/articles.xml";
            InputStream articleStream = new FileInputStream(filename);
            template.sendBody("direct:author", articleStream);
        } finally {
            camelContext.stop();
        }
    }
}

