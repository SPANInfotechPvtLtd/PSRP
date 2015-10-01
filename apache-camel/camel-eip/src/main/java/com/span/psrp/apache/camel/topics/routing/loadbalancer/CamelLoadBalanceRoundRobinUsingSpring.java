package com.span.psrp.apache.camel.topics.routing.loadbalancer;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mukesh.k on 10/1/2015.
 */
public class CamelLoadBalanceRoundRobinUsingSpring {
    public static final void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
                "loadbalancer/loadBalancingRoundRobinCamelContext.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(
                appContext, false);
        try {
            camelContext.start();
            Thread.sleep(5000);
        } finally {
            camelContext.stop();
            appContext.close();
        }
    }
}
