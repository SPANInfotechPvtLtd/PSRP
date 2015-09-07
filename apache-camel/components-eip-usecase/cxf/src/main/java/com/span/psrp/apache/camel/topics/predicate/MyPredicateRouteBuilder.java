

package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.builder.RouteBuilder;

public class MyPredicateRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        MyPredicate predicate = new MyPredicate();

        from("direct:start")
            .filter().method(predicate, "isWhatIWant")
                .to("mock:boston");
    }
}
