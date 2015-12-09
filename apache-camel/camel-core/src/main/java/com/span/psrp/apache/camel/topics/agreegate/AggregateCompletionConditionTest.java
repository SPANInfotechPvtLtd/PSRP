package com.span.psrp.apache.camel.topics.agreegate;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AggregateCompletionConditionTest {

	public static ProducerTemplate template;
	public static void main( String[] args ) throws Exception{
		AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("aggregateCompletionCondition-context.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
        try {
 		camelContext.start();
 		 template = camelContext.createProducerTemplate();
 		 template.sendBodyAndHeader("direct:in", "One", "group", "odd");
         template.sendBodyAndHeader("direct:in", "Two", "group", "even");
         Thread.sleep(1000);
         camelContext.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
}
