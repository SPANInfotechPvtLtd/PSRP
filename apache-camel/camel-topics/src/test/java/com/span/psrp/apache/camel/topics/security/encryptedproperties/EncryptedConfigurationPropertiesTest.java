package com.span.psrp.apache.camel.topics.security.encryptedproperties;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.After;
import org.junit.Before;
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
 * 
 * how to make create encrypt password please see README.md
 */

public class EncryptedConfigurationPropertiesTest  {

	private CamelContext camelContext;
	
	@Before
	public void setUpContext() throws Exception {
		this.camelContext = new DefaultCamelContext();
		
		JasyptPropertiesParser propParser = new JasyptPropertiesParser();
		propParser.setPassword("span");

		PropertiesComponent propComponent = new PropertiesComponent();
		propComponent.setLocation("classpath:config.properties");
		propComponent.setPropertiesParser(propParser);

		camelContext.addComponent("properties", propComponent);
		
		camelContext.addRoutes(new EncryptedConfigurationProperties());
		camelContext.start();
	}
	
	@After
	public void cleanUpContext() throws Exception {
		camelContext.stop();
	}


	@Test
	public void testPropertiesLoaded() throws InterruptedException {
		
		MockEndpoint mockOut = camelContext.getEndpoint("mock:out", MockEndpoint.class);
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		
		mockOut.setExpectedMessageCount(1);
		mockOut.message(0).header("userPassword").isEqualTo("span1234");

		producerTemplate.sendBody("direct:in", "direct:in");

		mockOut.assertIsSatisfied();
	}
}
