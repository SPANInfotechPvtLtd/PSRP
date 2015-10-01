package com.span.psrp.apache.camel.topics.routing.multicast;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class CamelMulticastSpringExample {
    public static final void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "multicast/applicationContext.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(
                appContext, false);
        try {
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBody("direct:start", "Multicast");
        } finally {
            camelContext.stop();
        }
    }
}
