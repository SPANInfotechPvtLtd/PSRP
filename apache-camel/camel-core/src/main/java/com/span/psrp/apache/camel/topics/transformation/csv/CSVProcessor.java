package com.span.psrp.apache.camel.topics.transformation.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class CSVProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.print("*************");
		final List<Map<String, BookModel>> response = (ArrayList<Map<String,BookModel>>) exchange.getIn().getBody();
		 Map<String, BookModel> response1 = response.get(0);
         System.out.print(1 +response1.size());
		 Map.Entry<String, BookModel> entry1 = response1.entrySet().iterator().next();
         System.out.print("*********************"+BookModel.class.getName()+entry1.getKey()+"\n");
         System.out.print("*********************"+entry1.getValue());
		
	}
	
}
