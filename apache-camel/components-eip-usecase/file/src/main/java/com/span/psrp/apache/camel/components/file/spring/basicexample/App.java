package com.span.psrp.apache.camel.components.file.spring.basicexample;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException{
    	AbstractApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext-camel.xml");
    	ctx.start();
    	System.out.println("Entered>>>>>");
    	Thread.sleep(5*60*1000);
    	ctx.stop();
    }
}
