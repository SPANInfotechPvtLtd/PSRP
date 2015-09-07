
package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;

public class MyPredicateInlineRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .filter(new Predicate() {
                public boolean matches(Exchange exchange) {
                    final String body = exchange.getIn().getBody(String.class);
                    System.out.println("************** MyPredicateInlineRouteBuilder");
                    return ((body != null) && body.contains("Boston"));
                }
            })
                .to("direct:boston");
        from("direct:boston").process(new LogProcessor());
    }
}
