package com.span.psrp.apache.camel.topics.propertieswithcustomexpression; 

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
 
public class CustomExpression implements Expression {

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {

        String body = exchange.getIn().getBody(String.class);
        Object mydata;
        if (body.contains("amount")) {
            mydata = body.substring(7, body.length());
        } else {
            mydata = 5;

        }

        return exchange.getContext().getTypeConverter().convertTo(type,mydata);
    }
}
