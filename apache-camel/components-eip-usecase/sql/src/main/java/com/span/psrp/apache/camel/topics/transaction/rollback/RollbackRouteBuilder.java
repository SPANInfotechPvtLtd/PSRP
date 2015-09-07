package com.span.psrp.apache.camel.topics.transaction.rollback;

import org.apache.camel.RollbackExchangeException;
import org.apache.camel.builder.RouteBuilder;

public class RollbackRouteBuilder extends RouteBuilder {
	@Override
    public void configure() throws Exception {
        from("direct:transacted")
            .onException(RollbackExchangeException.class)
                .log("Caught rollback :P")
            .end()
            .transacted()
            .log("Processing message: ${body}")
            .setHeader("message", body())
            .to("sql:insert into audit_log (message) values (:#message)")
            .choice()
                .when(simple("${body} contains 'explode'"))
                    .log("Message cannot be processed further - rolling back insert")
                    .rollback("Message contained word 'explode'")
                .otherwise()
                    .log("Message processed successfully")
                    .to("mock:out");
    }
}

