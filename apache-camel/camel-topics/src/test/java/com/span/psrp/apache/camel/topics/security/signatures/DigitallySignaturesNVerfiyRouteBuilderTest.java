
package com.span.psrp.apache.camel.topics.security.signatures;

import java.security.KeyStore;
import java.security.SignatureException;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.crypto.DigitalSignatureConstants;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Digitally signing and verifying messages 
 * Digital signatures are a mechanism
 * for signing a message payload using public key, also known as asymmetric,
 * cryptography to prove the authenticity of a message. This scheme additionally
 * provides non-repudiation to a message exchange, meaning that a sender will
 * not be able to deny at a future point in time that the message was sent by
 * him/her. To use this mechanism, a system uses a pair of cryptographic keys
 * that are made up of a private key known only to itself, and a public key that
 * is freely given out to third parties. Before sending a message, the system
 * uses the private key to generate a message signature (a type of checksum)
 * based on the message contents, and appends it to the message. The receiving
 * system uses the sender's public key to verify the signature against the
 * message contents. The verification step proves that the message was not
 * changed after being signed and that the originating system was the one who
 * originally signed it.
 * 
 * See also Camel Crypto for Digital Signatures:
 * http://camel.apache.org/cryptodigital-signatures.html 
 * Spring Crypto Utils:http://springcryptoutils.com/index.html 
 * Key and Certificate Management Tool (keytool):
 * http://docs.oracle.com/javase/7/docs/technotes/tools/solaris/keytool.html
 * Standard Algorithm Names Documentation:
 * http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
 */
public class DigitallySignaturesNVerfiyRouteBuilderTest extends CamelTestSupport {

    private final Logger log = LoggerFactory.getLogger(DigitallySignaturesNVerfiyRouteBuilderTest.class);

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new DigitallySignaturesNVerfiyRouteBuilder();
    }
    /**
     * If using Java, load and register the java.security.KeyStore instances within the
	 *	Camel context as follows:
	 * JKS is the default keystore type used to hold key pairs
     */
    @Override
    protected CamelContext createCamelContext() throws Exception {
        final String keyStorePassword = "keystorePassword";
        final String trustStorePassword = "truststorePassword";

        SimpleRegistry registry = new SimpleRegistry();

        KeyStore keyStore = KeyStore.getInstance("JKS");

        ClassLoader classLoader = getClass().getClassLoader();
        log.info("Information about keystore from [{}]", classLoader.getResource("keystore.jks").toString());
        keyStore.load(classLoader.getResourceAsStream("keystore.jks"), keyStorePassword.toCharArray());
        registry.put("keyStore", keyStore);

        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(classLoader.getResourceAsStream("truststore.jks"), trustStorePassword.toCharArray());
        registry.put("trustStore", trustStore);

        return new DefaultCamelContext(registry);
    }

    @Test
    public void testMessageSigning() throws InterruptedException {
        MockEndpoint mockVerified = getMockEndpoint("mock:verified");
        mockVerified.setExpectedMessageCount(1);

        template.sendBody("direct:sign", "DigitallySignaturesNVerfiyRouteBuilder");

        assertMockEndpointsSatisfied();
    }

 }
