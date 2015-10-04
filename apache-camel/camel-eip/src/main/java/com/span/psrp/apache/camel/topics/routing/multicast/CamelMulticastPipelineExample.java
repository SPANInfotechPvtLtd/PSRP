package com.span.psrp.apache.camel.topics.routing.multicast;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class CamelMulticastPipelineExample {
    public static final void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("myBean", new MyBean());
        jndiContext.bind("stringUtils", new StringUtils());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:start")
                            .multicast()
                            .pipeline()
                            .to("bean:myBean?method=addFirst")
                            .to("bean:stringUtils?method=upperCase")
                            .to("mock:out")
                            .end()

                            .pipeline()
                            .to("bean:myBean?method=addSecond")
                            .to("bean:stringUtils?method=upperCase")
                            .to("mock:out")
                            .end()

                            .pipeline()
                            .to("bean:myBean?method=addThird")
                            .to("bean:stringUtils?method=upperCase")
                            .to("mock:out")
                            .end()
                            .end()
                            .setBody(simple("Final Output: ${body}"))
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
