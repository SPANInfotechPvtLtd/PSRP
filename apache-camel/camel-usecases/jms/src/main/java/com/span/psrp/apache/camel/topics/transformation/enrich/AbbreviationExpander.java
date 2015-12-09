package com.span.psrp.apache.camel.topics.transformation.enrich;

public class AbbreviationExpander {

	public String expand(String abbreviation) {
        if ("MA".equalsIgnoreCase(abbreviation)) {
            return "Massachusetts";
        }

        if ("CA".equalsIgnoreCase(abbreviation)) {
            return "California";
        }

        throw new IllegalArgumentException("Unknown abbreviation '" + abbreviation + ";");
    }
}
