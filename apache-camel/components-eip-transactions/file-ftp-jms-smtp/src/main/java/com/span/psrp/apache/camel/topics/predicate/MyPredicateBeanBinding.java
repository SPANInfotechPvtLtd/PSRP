package com.span.psrp.apache.camel.topics.predicate;

import org.apache.camel.language.XPath;

public class MyPredicateBeanBinding {
    public boolean isBoston(@XPath("/someXml/city/text()") String city) {
    	System.out.println("predicate examples"+"Boston");
        return "Boston".equals(city);
    }
    
    public boolean isNewYork(@XPath("/someXml/city/text()") String city) {
    	System.out.println("predicate examples" +"newYork");
        return "NewYork".equals(city);
    }
}
