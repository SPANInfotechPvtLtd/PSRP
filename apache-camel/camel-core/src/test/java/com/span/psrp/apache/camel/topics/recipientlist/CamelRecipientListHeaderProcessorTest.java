package com.span.psrp.apache.camel.topics.recipientlist;

import org.junit.Test;

public class CamelRecipientListHeaderProcessorTest {

    @Test
    public void listHeaderProcessor() throws Exception {
        CamelRecipientListHeaderProcessor listHeaderProcessor = new CamelRecipientListHeaderProcessor();
        listHeaderProcessor.listHeaderProcessor("Sam Joined", "new");
        listHeaderProcessor.listHeaderProcessor("John Resigned", "resigns");
    }
}