package com.span.psrp.apache.camel.topics.recipientlist;

import org.junit.Test;

public class CamelRecipientListIgnoreInvalidEndpointsTest {

    @Test
    public void ignoreInvalidEndpoint() throws Exception {
        CamelRecipientListIgnoreInvalidEndpoints ignoreInvalidEndpoints = new CamelRecipientListIgnoreInvalidEndpoints();
        ignoreInvalidEndpoints.ignoreInvalidEndpoint("Sam Joined", "direct:account,direct:hr,some:unknownEndpoint");
    }
}