package com.span.psrp.apache.camel.topics.smtp;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class SendEmailOnExceptionUsingCamelSMTP {
	public static final void main(String[] args) throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("myBean", new MyBean());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        
        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    onException(Exception.class)
                            .handled(true)
                            .to("log:onException123")
                            .transform(constant("Exception thrown. Stop routing.."))
                    		.to("stream:out");

                    from("direct:mail")
                            .setHeader("Subject", constant("Camel SMTP Test Mail"))
                            .setHeader("From", constant("abc123@gmail.com"))
                            .setHeader("To", constant("abc123@gmail.com"))
                            .to("smtps://smtp.gmail.com:465?username=abc123@gmail.com&password='abc123'&debugMode=true")
                            .transform(constant("Email Sent Successfully!!"))
                            .to("stream:out");

                    // Note : https://www.google.com/settings/security/lesssecureapps : Turn On, here.

                    from("direct:start")
                            .multicast()
                            .stopOnException()
                            .to("direct:a", "direct:b")
                            .end()
                            .setBody(simple("Final Output after multicast ${body}"))
                    		.to("stream:out");

                    from("direct:a")
                            .process(new NumberValidateProcessor())
                            .setBody(simple("Received ${body} from direct:a"))
                    		.to("stream:out");

                    from("direct:b")
                            .setBody(simple("Received ${body} from direct:b"))
                    		.to("stream:out");
                }
            });
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            template.sendBody("direct:start", "1");
            template.sendBody("direct:start", "one");
            template.sendBody("direct:mail", "Input value 'one' is incorrect!");				
        } finally {
            camelContext.stop();
        }
    }
}
