
package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.builder.RouteBuilder;

import static org.apache.camel.builder.PredicateBuilder.and;

public class MyPredicateCompoundRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        MyPredicate predicate = new MyPredicate();
        from("direct:start")
            .filter(and(xpath("/someXml/city"), method(predicate, "isBoston")))
                .to("mock:boston");
    }
}
