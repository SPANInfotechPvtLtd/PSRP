package com.span.psrp.apache.camel.topics.routing.loadbalancer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.processor.loadbalancer.SimpleLoadBalancerSupport;

public class MyCustomLoadBalancer extends SimpleLoadBalancerSupport {
	
	public void process(Exchange exchange) throws Exception {
        Processor target = chooseProcessor(exchange);
        target.process(exchange);
    }

    protected Processor chooseProcessor(Exchange exchange) {
        String type = exchange.getIn().getHeader("type", String.class);
        if ("gold".equals(type)) {
            return getProcessors().get(0);
        } else {
            return getProcessors().get(1);
        }
    }
}
