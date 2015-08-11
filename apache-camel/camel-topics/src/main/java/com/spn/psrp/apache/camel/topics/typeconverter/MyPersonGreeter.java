
package com.spn.psrp.apache.camel.topics.typeconverter;

public class MyPersonGreeter {
    public String sayHello(MyPerson person) {
        return "Hello " + person.getFirstName() + " " + person.getLastName();
    }
}
