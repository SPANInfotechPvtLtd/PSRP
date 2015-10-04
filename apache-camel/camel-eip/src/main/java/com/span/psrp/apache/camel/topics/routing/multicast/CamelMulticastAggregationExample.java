package com.span.psrp.apache.camel.topics.routing.multicast;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class CamelMulticastAggregationExample {
    public static final void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("myBean", new MyBean());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:start")
                            .multicast()
                            .aggregationStrategy(new JoinReplyAggregationStrategy())
                            .to("direct:a", "direct:b", "direct:c")
                            .end()
                            .to("mock:out");

                    from("direct:a")
                            .to("bean:myBean?method=addFirst");

                    from("direct:b")
                            .to("bean:myBean?method=addSecond");

                    from("direct:c")
                            .to("bean:myBean?method=addThird");
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
