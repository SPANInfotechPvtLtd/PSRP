
package com.span.psrp.apache.camel.usecases.cxf;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UpdateCustomerProcessor implements Processor {
    public static final Logger log = LoggerFactory.getLogger(UpdateCustomerProcessor.class);

    public void process(Exchange exchng) throws Exception {
       
    	System.out.println("Request: Get **************** UpdateCustomerProcessor ");

        // No response paramters (this is a void function) so we set the out message.
        //
        //exchng.getOut().setBody(new Object[] {});
    }

}
