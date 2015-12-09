package com.span.psrp.apache.camel.usecases.cxf;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techiepassion.ws.product_service.types.ProductRequest;
import com.techiepassion.ws.product_service.types.ProductResponse;

public class CamelCxfExample {
	private static ProducerTemplate template;

	public static void main(String[] args) throws Exception {
		
		System.setProperty("port1", "9000");
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"META-INF/ws-context.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		try {
			template = camelContext.createProducerTemplate();
			System.out.println("Start camel context");
			camelContext.start();
			printProductDetails("P01");
			printProductDetails("P02");
			printProductDetails("Uknown");
		} finally {
			System.out.println("Stop camel context");
			camelContext.stop();
		}
	}

	private static void printProductDetails(String id) {
		try {
			System.out.println("Request: Get " + id + " details ");
			ProductRequest request = new ProductRequest();
			request.setId(id);
			ProductResponse response = template.requestBody("direct:start",
					request, ProductResponse.class);
			System.out.println("Response: Id: " + response.getId() + ", Product: "
					+ response.getDescription() + ", Price: " + response.getPrice());
		} catch (CamelExecutionException p) {
			System.out.println(p.getCause());
		}
	}
}
