package com.span.psrp.apache.camel.topics.security.encryptedproperties;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Demonstrates the use of encrypted properties in Camel routes. 
 * Encrypting configuration properties.
 * To access these resources we often need to store
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

public class EncryptedConfigurationPropertiesTest extends CamelTestSupport {

	@Override
	public RouteBuilder createRouteBuilder() {
		return new EncryptedConfigurationProperties();
	}

	/*
	 * If using Java only, you need to instantiate the parser and
	 * PropertiesComponent, and set the component on the Camel context before it
	 * is started:
	 */
	@Override
	public CamelContext createCamelContext() {
		JasyptPropertiesParser propParser = new JasyptPropertiesParser();
		propParser.setPassword("span");

		PropertiesComponent propComponent = new PropertiesComponent();
		propComponent.setLocation("classpath:config.properties");
		propComponent.setPropertiesParser(propParser);

		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addComponent("properties", propComponent);
		return camelContext;
	}

	@Test
	public void testPropertiesLoaded() throws InterruptedException {
		MockEndpoint mockOut = getMockEndpoint("mock:out");
		mockOut.setExpectedMessageCount(1);
		mockOut.message(0).header("userPassword").isEqualTo("span1234");

		template.sendBody("direct:in", "direct:in");

		assertMockEndpointsSatisfied();
	}
}
