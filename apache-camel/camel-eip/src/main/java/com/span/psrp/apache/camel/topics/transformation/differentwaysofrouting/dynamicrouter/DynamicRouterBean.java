package com.span.psrp.apache.camel.topics.transformation.differentwaysofrouting.dynamicrouter;
import org.apache.camel.Exchange;
import org.apache.camel.Header;


public class DynamicRouterBean {


    public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previous) {
        return whereToGo(body, previous);
    }

    /**
     * Method which computes where to go next
     */
    private String whereToGo(String body, String previous) {
        System.out.println(previous);
        if (previous == null) {
            // 1st time
            System.out.println(previous);
            return "mock://a";
        } else if ("mock://a".equals(previous)) {
            System.out.println(previous);
            // 2nd time - transform the message body using the simple language
            return "language://simple:Bye ${body}";
        } else {
            // no more, so return null to indicate end of dynamic router
            return null;
        }
    }
}
