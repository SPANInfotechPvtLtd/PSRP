

package com.span.psrp.apache.camel.topics.error.util;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlakyProcessor {
    private final static Logger LOG = LoggerFactory.getLogger(FlakyProcessor.class);

    public void doSomething(Exchange exchange) throws FlakyException {
    	System.out.print("FlakyProcessor Example"+"\n");
        if (exchange.getProperty("optimizeBit", false, boolean.class)) {
           LOG.info("FlakyProcessor works with optimizationBit set");
            return;
        }

        if ("KaBoom".equalsIgnoreCase(exchange.getIn().getBody(String.class))) {
            LOG.error("Throwing FlakyException");
            //System.out.print("Throwing FlakyException"+"\n");
            throw new FlakyException("FlakyProcessor has gone Flaky");
        }
    }
}
