package com.span.psrp.apache.camel.topics.recipientlist;

import org.junit.Test;

public class CamelRecipientListTokenizerTest {

    @Test
    public void tokonizerTest() throws Exception {
        CamelRecipientListTokenizer listTokenizer = new CamelRecipientListTokenizer();
        listTokenizer.listTokenizer("Sam Joined", "direct:account>direct:hr>direct:manager");
    }
}