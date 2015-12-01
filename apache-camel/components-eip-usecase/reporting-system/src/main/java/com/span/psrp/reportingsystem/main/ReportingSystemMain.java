package com.span.psrp.reportingsystem.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportingSystemMain {

    private static ProducerTemplate template;
    private static ConsumerTemplate consumerTemplate;

    public static void main(final String[] args) throws Exception {
        AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("reporting-system-context.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
        template = camelContext.createProducerTemplate();
        System.out.println("Start camel context");
        appContext.start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Entered>>>>>");
        appContext.stop();
    }
}
