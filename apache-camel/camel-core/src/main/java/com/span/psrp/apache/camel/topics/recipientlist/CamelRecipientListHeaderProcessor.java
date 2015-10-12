package com.span.psrp.apache.camel.topics.recipientlist;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelRecipientListHeaderProcessor {

    private final static Logger log = LoggerFactory.getLogger(CamelRecipientListHeaderProcessor.class);

    public void listHeaderProcessor(final String message, final String action) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {

                @Override
                public void configure() {
                    from("direct:start")
                            .process(new Processor() {

                                @Override
                                public void process(final Exchange exchange) throws Exception {
                                    String recipients = "direct:hr";
                                    String employeeAction = exchange.getIn().getHeader("employee_action", String.class);
                                    if (employeeAction.equals("new")) {
                                        recipients += ",direct:account,direct:manager";
                                    } else if (employeeAction.equals("resigns")) {
                                        recipients += ",direct:account";
                                    }
                                    exchange.getIn().setHeader("departments", recipients);
                                }
                            })
                            .recipientList(header("departments"));

                    from("direct:account").log("Account department notified '${body}'");

                    from("direct:hr").log("HR department notified '${body}'");

                    from("direct:manager").log("Manager notified '${body}'");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            log.debug("************************");
            template.sendBodyAndHeader("direct:start", message, "employee_action", action);
            log.debug("************************");
        } finally {
            camelContext.stop();
        }
    }
}