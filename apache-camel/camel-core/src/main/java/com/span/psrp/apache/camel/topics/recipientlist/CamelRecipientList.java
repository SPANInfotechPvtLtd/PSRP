package com.span.psrp.apache.camel.topics.recipientlist;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Send a message related to an employee to a list of recipients.
 * The following route will take the list of recipients from a header named departments.
 * You will use the recipientList DSL statement to extract the list of end points from the expression.
 * 
 * @author mohanarao_sv
 *
 */
public class CamelRecipientList {

    public void processRecipientList(final String message) {
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {

                @Override
                public void configure() {
                    from("direct:start").recipientList(header("departments"));

                    from("direct:account").log("Account department notified '${body}'");

                    from("direct:hr").log("HR department notified '${body}'");

                    from("direct:manager").log("Manager notified '${body}'");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBodyAndHeader("direct:start", message, "departments", "direct:account,direct:hr,direct:manager");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                camelContext.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}