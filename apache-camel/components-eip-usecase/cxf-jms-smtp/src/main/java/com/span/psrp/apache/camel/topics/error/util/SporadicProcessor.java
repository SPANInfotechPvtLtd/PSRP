
package com.span.psrp.apache.camel.topics.error.util;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SporadicProcessor {
	
    private final static Logger LOG = LoggerFactory.getLogger(SporadicProcessor.class);
    private long lastCall;

    public void doSomething(Exchange exchange) throws SporadicException {
        if (exchange.getIn().getHeader(Exchange.REDELIVERED, false, boolean.class)) {
            exchange.setProperty("SporadicDelay", (System.currentTimeMillis() - lastCall));
            LOG.info("SporadicProcessor works on retry");
            return;
        }
        lastCall = System.currentTimeMillis();
        LOG.info("SporadicProcessor fails");
        throw new SporadicException("SporadicProcessor is unavailable");
    }
}
