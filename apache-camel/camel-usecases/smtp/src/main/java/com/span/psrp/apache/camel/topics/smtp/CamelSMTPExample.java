package com.span.psrp.apache.camel.topics.smtp;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by mukesh.k on 10/1/2015.
 */
public class CamelSMTPExample {
    
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        try{
        	context.addRoutes(new RouteBuilder() {
                public void configure() throws Exception {
                    from("direct:mail")
                            .setHeader("Subject", constant("Camel SMTP Test Mail"))
                            .setHeader("From", constant("abc123@gmail.com"))
                            .setHeader("To", constant("abc123@gmail.com"))
                            .to("smtps://smtp.gmail.com:465?username=abc123@gmail.com&password='abc123'&debugMode=true")
                            .transform(constant("Email Sent Successfully!!"))
                            .to("stream:out");

                    // Note : https://www.google.com/settings/security/lesssecureapps : Turn On, here.
                }
        	});
        	ProducerTemplate template = context.createProducerTemplate();
        	context.start();
        	template.sendBody("direct:mail", "You Welcome!");
        }
        finally{
        	context.stop();
        }   
    }
}
