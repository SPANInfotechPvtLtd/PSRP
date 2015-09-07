
package com.span.psrp.apache.camel.topics.binding;

import org.apache.camel.builder.RouteBuilder;

public class MyBeanRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:normal").bean(MyBean.class, "sayHello(${body}, false)");

        from("direct:hipster").bean(MyBean.class, "sayHello(${body}, true)");

        from("direct:undecided").bean(MyBean.class, "sayHello(${body}, ${header.hipster})");
    }
}
