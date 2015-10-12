package com.span.psrp.apache.camel.topics.recipientlist;

import org.junit.Test;

public class CamelRecipientListRouterTest {

    @Test
    public void joined() throws Exception {
        CamelRecipientListRouter listRouter = new CamelRecipientListRouter();
        Employee sam = new Employee("Sam");
        sam.setNew(true);
        sam.setMessage("Joined");
        listRouter.recipientListRouter(sam);
    }

    @Test
    public void onLeave() throws Exception {
        CamelRecipientListRouter listRouter = new CamelRecipientListRouter();
        Employee john = new Employee("John");
        john.setOnLeave(true);
        john.setMessage("On Leave");
        listRouter.recipientListRouter(john);
    }

    @Test
    public void promoted() throws Exception {
        CamelRecipientListRouter listRouter = new CamelRecipientListRouter();
        Employee roy = new Employee("Roy");
        roy.setPromoted(true);
        roy.setMessage("Promoted");
        listRouter.recipientListRouter(roy);
    }

    @Test
    public void employeeJoined() throws Exception {
        CamelRecipientListRouter listRouter = new CamelRecipientListRouter();
        Employee ram = new Employee("Ram");
        ram.setResigning(true);
        ram.setMessage("Resigning");
        listRouter.recipientListRouter(ram);
    }

}