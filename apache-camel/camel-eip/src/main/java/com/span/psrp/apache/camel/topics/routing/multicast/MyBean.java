package com.span.psrp.apache.camel.topics.routing.multicast;

/**
 * Created by mukesh.k on 9/30/2015.
 */
public class MyBean {
    
    public String addFirst(String body) {
    	System.out.print("first destination"+body+"\n");
        return body + " first destination";
    }

    public String addSecond(String body) {
    	System.out.print("second destination"+body+"\n");
        return body + " second destination";
    }

    public String addThird(String body) {
    	System.out.print("third destination"+body+"\n");
        return body + " third destination";
    }
}
