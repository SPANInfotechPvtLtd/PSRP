package com.span.psrp.apache.camel.topics.sql.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {

	public static void main( String[] args )
    {
    	AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("sql-camel.xml");
    	ctx.start();
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("Entered>>>>>");
    	ctx.stop();
    }
}
