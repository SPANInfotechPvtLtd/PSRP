

package com.span.psrp.apache.camel.topics.error.util;

public class FlakyException extends Exception {
    public FlakyException() {
        super();
    }

    public FlakyException(String message) {
        super(message);
    }
}
