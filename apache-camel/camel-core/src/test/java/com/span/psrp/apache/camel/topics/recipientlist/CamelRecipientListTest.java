package com.span.psrp.apache.camel.topics.recipientlist;

import org.junit.Test;

/**
 * 
 * 
 * 
 * @author mohanarao_sv
 *
 */
public class CamelRecipientListTest {

    @Test
    public void recipientListTest() {
        CamelRecipientList camelRecipientList = new CamelRecipientList();
        camelRecipientList.processRecipientList("Sam Joined");
    }
}