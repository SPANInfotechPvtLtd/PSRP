
package com.span.psrp.apache.camel.topics.security.encryption;

import java.security.Key;
import java.security.KeyStore;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encrypting and decrypting a message Camel's Crypto Component is used when you
 * need to encrypt and decrypt an entire message. It provides a Camel Data
 * Format that allows you to marshal (encrypt) or unmarshal (decrypt) your data.
 * The Crypto Component supports both symmetric (using a shared password) and
 * asymmetric (using public key of recipient) encryption—the latter through PGP.
 * This recipe will show you how to configure basic symmetric encryption. It
 * will show both marshaling (encrypting) and unmarshaling (decrypting) data.
 * These actions would normally be done in different Camel routes on different
 * systems.
 * 
 * See also 
 * Camel Crypto: http://camel.apache.org/crypto.html 
 * Camel Data Format:http://camel.apache.org/data-format.html 
 * Spring Crypto Utils:http://springcryptoutils.com/index.html 
 * Key and Certificate Management Tool (keytool): http://docs.oracle.com/
 * javase/7/docs/technotes/tools/solaris/keytool.html 
 * Standard Algorithm Names Documentation : 
 * http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html 
 * Bouncy Castle Java Cryptography API: http://www.bouncycastle.org/java.html
 */

public class EncryptionDecrptingMessageRouteBuilderTest extends CamelTestSupport {
    private final Logger log = LoggerFactory.getLogger(EncryptionDecrptingMessageRouteBuilderTest.class);

    /**
     * If using Java only, load the shared key using a java.security.Keystore instanceas follows:
     */
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        ClassLoader classLoader = getClass().getClassLoader();
        log.info("Loading keystore from [{}]", classLoader.getResource("shared.jceks").toString());
        keyStore.load(classLoader.getResourceAsStream("shared.jceks"), "sharedKeystorePassword".toCharArray());

        Key sharedKey = keyStore.getKey("shared", "sharedKeyPassword".toCharArray());
        return new EncryptionDecrptingMessageRouteBuilder(sharedKey);
    }

    @Test
    public void testMessageEncryption() throws InterruptedException {
        MockEndpoint mockDecrypted = getMockEndpoint("mock:decrypted");
        mockDecrypted.setExpectedMessageCount(1);
        mockDecrypted.message(0).body().isEqualTo("foo");

        template.sendBody("direct:encrypt", "foo");

        assertMockEndpointsSatisfied();
    }
}
