
package com.span.psrp.apache.camel.topics.predicate;

public class MyPredicate {
    public boolean isBoston(String body) {
    	System.out.println("Predicate Compound Route Builder");
        return ((body != null) && body.contains("Boston"));
    }
}
