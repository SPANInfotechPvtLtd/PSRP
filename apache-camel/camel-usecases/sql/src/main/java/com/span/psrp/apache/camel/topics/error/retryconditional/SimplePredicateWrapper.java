
package com.span.psrp.apache.camel.topics.error.retryconditional;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.SimpleBuilder;

public class SimplePredicateWrapper {
    private Predicate predicate;

    public void setSimplePredicate(String expression) {
        this.predicate = SimpleBuilder.simple(expression, Boolean.class);
    }

    public boolean matches(Exchange exchange) {
        return predicate.matches(exchange);
    }
}
