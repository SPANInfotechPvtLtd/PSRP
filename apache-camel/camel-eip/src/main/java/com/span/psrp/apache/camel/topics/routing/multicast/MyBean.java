package com.span.psrp.apache.camel.topics.routing.multicast;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class MyBean {
    
    public String addFirst(String body) {
        return body + " first destination";
    }

    public String addSecond(String body) {
        return body + " second destination";
    }

    public String addThird(String body) {
        return body + " third destination";
    }
}
