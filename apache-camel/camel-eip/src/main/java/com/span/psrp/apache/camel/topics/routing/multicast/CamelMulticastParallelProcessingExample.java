package com.span.psrp.apache.camel.topics.routing.multicast;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class CamelMulticastParallelProcessingExample {
    public static final void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("myBean", new MyBean());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    ExecutorService executor = Executors.newFixedThreadPool(12);
                    from("direct:start")
                            .multicast()
                            .parallelProcessing()
                            .executorService(executor)
                            .to("direct:a", "direct:b", "direct:c");

                    from("direct:a")
                            .to("bean:myBean?method=addFirst")
                            .setBody(simple("body: ${body}, thread: ${threadName}"))
                            .to("mock:out");

                    from("direct:b")
                            .to("bean:myBean?method=addSecond")
                            .setBody(simple("body: ${body}, thread: ${threadName}"))
                            .to("mock:out");

                    from("direct:c")
                            .to("bean:myBean?method=addThird")
                            .setBody(simple("body: ${body}, thread: ${threadName}"))
                            .to("mock:out");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBody("direct:start", "Multicast");
        } finally {
            camelContext.stop();
        }
    }
}
