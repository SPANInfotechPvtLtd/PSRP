package com.span.psrp.apache.camel.topics.recipientlist;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelRecipientListRouter {

    private final static Logger log = LoggerFactory.getLogger(CamelRecipientListRouter.class);

    public void recipientListRouter(final Employee employee) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("empRouter", new EmployeeRouter());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        try {
            camelContext.addRoutes(new RouteBuilder() {

                @Override
                public void configure() {
                    from("direct:start").recipientList(method("empRouter", "routeEmployee"));

                    from("direct:account").log("Account department notified '${body}'");

                    from("direct:hr").log("HR department notified '${body}'");

                    from("direct:manager").log("Manager notified '${body}'");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            log.debug("************************");
            template.sendBody("direct:start", employee);
            log.debug("************************");
        } finally {
            camelContext.stop();
        }
    }
}