package com.span.psrp.apache.camel.topics.security.encryptedproperties;

import org.apache.camel.builder.RouteBuilder;

/**
 * Demonstrates the use of encrypted properties in Camel routes. Encrypting
 * configuration properties. To access these resources we often need to store
 * sensitive information such as passwords. It is considered bad practice to
 * keep these in plain text, as that would allow the reader to freely access
 * those resources. To address this, we need to overlay another mechanism that
 * allows us to hide passwords through encryption. This recipe will show you how
 * to use the Camel Jasypt Component to manage encrypted configuration values
 * within Camel. 
 * see also 
 * Camel Jasypt Component: http://camel.apache.org/jasypt.html 
 * Jasypt (Java Simplified Encryption):http://jasypt.org/ 
 * Standard Algorithm Names Documentation:
 * http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 */
public class EncryptedConfigurationProperties extends RouteBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.builder.RouteBuilder#configure() Inside your route
	 * you can now use encrypted properties as usual; encrypted properties will
	 * be decrypted at runtime
	 */
	@Override
	public void configure() throws Exception {
		from("{{start.endpoint}}").setHeader("userPassword", simple("{{user.password}}")).log("{{log.message}}")
				.to("{{end.endpoint}}");
	}
}