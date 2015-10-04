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
public class CamelSplitXmlTokenizeStreamingExample {
    public static final void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:authorStreaming")
                            .log("Find all the authors using tokenizer/streaming")
                            .split().tokenizeXML("author").streaming().process(new Processor() {
								@Override
								public void process(Exchange exchange) throws Exception {
									System.out.print("*******"+exchange.getIn().getBody(String.class));
									
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
            template.sendBody("direct:authorStreaming", articleStream);
        } finally {
            camelContext.stop();
        }
    }
}
