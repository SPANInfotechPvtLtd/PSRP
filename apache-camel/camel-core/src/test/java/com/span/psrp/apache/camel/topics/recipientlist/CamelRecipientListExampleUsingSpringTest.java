package com.span.psrp.apache.camel.topics.recipientlist;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelRecipientListExampleUsingSpringTest {

    private final static Logger log = LoggerFactory.getLogger(CamelRecipientListExampleUsingSpringTest.class);

    public static final void main(final String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("recipient-list.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
        try {
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            log.debug("************************");
            Employee sam = new Employee("Sam");
            sam.setNew(true);
            sam.setMessage("Joined");

            template.sendBody("direct:start", sam);

            log.debug("************************");

            Employee john = new Employee("John");
            john.setOnLeave(true);
            john.setMessage("On Leave");
            template.sendBody("direct:start", john);

            log.debug("************************");

            Employee roy = new Employee("Roy");
            roy.setPromoted(true);
            roy.setMessage("Promoted");
            template.sendBody("direct:start", roy);

            log.debug("************************");

            Employee ram = new Employee("Ram");
            ram.setResigning(true);
            ram.setMessage("Resigning");
            template.sendBody("direct:start", ram);

            log.debug("************************");
        } finally {
            camelContext.stop();
        }
    }
}