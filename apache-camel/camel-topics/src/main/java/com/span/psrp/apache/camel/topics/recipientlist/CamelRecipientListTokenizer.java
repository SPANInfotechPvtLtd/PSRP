package com.span.psrp.apache.camel.topics.recipientlist;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelRecipientListTokenizer {

    public void listTokenizer(final String message, final String tokens) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {

                @Override
                public void configure() {
                    from("direct:start").recipientList(header("departments").tokenize(">"));

                    from("direct:account").log("Account department notified '${body}'");

                    from("direct:hr").log("HR department notified '${body}'");

                    from("direct:manager").log("Manager notified '${body}'");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBodyAndHeader("direct:start", message, "departments", tokens);
        } finally {
            camelContext.stop();
        }
    }
}