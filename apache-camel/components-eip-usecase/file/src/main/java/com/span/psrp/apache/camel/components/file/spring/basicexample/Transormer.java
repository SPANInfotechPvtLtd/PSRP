package com.span.psrp.apache.camel.components.file.spring.basicexample;

public class Transormer {
	
	public String transformContent(String body){
		System.out.println("invoking the transformContent method");
		String upperCaseContent=body.toUpperCase();
		//System.out.println(object);
		return upperCaseContent;
	}

}
