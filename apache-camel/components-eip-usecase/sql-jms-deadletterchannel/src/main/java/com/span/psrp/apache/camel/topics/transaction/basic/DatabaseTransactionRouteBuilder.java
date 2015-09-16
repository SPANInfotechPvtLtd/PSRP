package com.span.psrp.apache.camel.topics.transaction.basic;

import org.apache.camel.builder.RouteBuilder;

public class DatabaseTransactionRouteBuilder extends RouteBuilder {
	
    @Override
    public void configure() throws Exception {
        from("direct:transacted").transacted()
            .log("Processing message: ${body}")
            .setHeader("message", body()).beanRef("auditLogHelper", "generateNewTopic")
            .to("sql:insert into audit_log (audit_id,message) values (:#auditid,:#auditUser)")
            .to("mock:out");
    }
}
