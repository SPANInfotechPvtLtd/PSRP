package com.span.psrp.apache.camel.topics.transformation.enrich;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.commons.lang.Validate;

public class MergeInReplacementText implements AggregationStrategy{

	public static final String ENRICH_EXAMPLE_ORIGINAL_BODY = "EnrichExample.originalBody";
    public static final String ENRICH_EXAMPLE_REPLACEMENT_STRING = "EnrichExample.replacementString";

    /**
     * When using this AggregationStrategy, this method must be called <b>before</b> the enrich call as this
     * method sets up the message body, and adds some properties needed by the aggregate method.
     */
    public void setup(Exchange exchange) {
        final String originalBody = exchange.getIn().getBody(String.class);

        exchange.setProperty(ENRICH_EXAMPLE_ORIGINAL_BODY, originalBody);

        final String enrichParameter = originalBody.substring(originalBody.lastIndexOf(" ") + 1);

        exchange.setProperty(ENRICH_EXAMPLE_REPLACEMENT_STRING, enrichParameter);

        exchange.getIn().setBody(enrichParameter);
    }

    @Override
    public Exchange aggregate(Exchange original, Exchange enrichResponse) {
        // The original.In.Body was changed to the replacement string, so need to retrieve property with original body
        final String originalBody = original.getProperty(ENRICH_EXAMPLE_ORIGINAL_BODY, String.class);
        Validate.notEmpty(originalBody,
            "The property '" + ENRICH_EXAMPLE_ORIGINAL_BODY + "' must be set with the original message body.");

        final String replacementString = original.getProperty(ENRICH_EXAMPLE_REPLACEMENT_STRING, String.class);
        Validate.notEmpty(replacementString,
            "The property '" + ENRICH_EXAMPLE_REPLACEMENT_STRING + "' must be set with the value to be replaced.");

        final String replacementValue = enrichResponse.getIn().getBody(String.class);

        // Use regular expression to replace the last occurrence of the replacement string
        final String mergeResult = originalBody.replaceAll(replacementString + "$", replacementValue);

        original.getIn().setBody(mergeResult);

        return original;
    }
}
