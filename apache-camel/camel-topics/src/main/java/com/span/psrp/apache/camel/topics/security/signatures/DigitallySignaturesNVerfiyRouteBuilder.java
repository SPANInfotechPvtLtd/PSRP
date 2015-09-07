package com.span.psrp.apache.camel.topics.security.signatures;

import org.apache.camel.builder.RouteBuilder;

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
public class DigitallySignaturesNVerfiyRouteBuilder extends RouteBuilder {
	/**
	 * To sign a message within your route, send the message to a crypto: endpoint
	 * containing details of the key to sign the message with
	 * To verify the message, send the message to a crypto: endpoint that defines which
	 * public key from the truststore should be used.
	 * In order to verify a signed message, we need to know which public key in the
	 * truststore to use; we do this by using the alias attribute.
	 */

	@Override
	public void configure() throws Exception {
		from("direct:sign").log("Signing message")
				.to("crypto:sign://usingKeystore?keystore=#keyStore&alias=system_a&password=keyPasswordA")
				.to("log:out?showHeaders=true")
				.log("Message signed")
				.to("mock:signed")
				.to("direct:verify");

		from("direct:verify").log("Verifying message")
				.to("crypto:verify://usingKeystore?keystore=#trustStore&alias=system_a")
				.log("Message verified")
				.to("mock:verified");
	}
}
