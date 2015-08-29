package com.span.psrp.apache.camel.topics.security.encryption;

import java.security.Key;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.apache.commons.lang.Validate;

/**
 * Encrypting and decrypting a message 
 * Camel's Crypto Component is used when you
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
public class EncryptionDecrptingMessageRouteBuilder extends RouteBuilder {

	private final Key sharedKey;

	public EncryptionDecrptingMessageRouteBuilder(Key sharedKey) {
		Validate.notNull(sharedKey, "sharedKey is null");
		this.sharedKey = sharedKey;
	}

	@Override
	public void configure() throws Exception {
		CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);

		from("direct:encrypt")
		.log("Encrypting message")
		.marshal(sharedKeyCrypto)
		.log("Message encrypted: ${body}")
		.to("direct:decrypt");

		from("direct:decrypt")
		.log("Decrypting message")
		.unmarshal(sharedKeyCrypto)
		.log("Message decrypted: ${body}")
		.to("mock:decrypted");
	}
}