

package com.span.psrp.apache.camel.topics.binding;

public class MyBean {
    public String sayHello(String name, boolean hipster) {
    	System.out.print(name+" ************* "+hipster);
        return (hipster) ? ("Yo " + name) : ("Hello " + name);
    }
}
