package com.span.psrp.apache.camel.topics.transformation.csv.main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.CastUtils;

import com.span.psrp.apache.camel.topics.transformation.csv.BookModel;
import com.span.psrp.apache.camel.topics.transformation.csv.CsvRouteBuilder;

public class CSVMain {
	
	ProducerTemplate template;
	
	public static void main( String[] args )
    {
		CsvRouteBuilder routeBuilder=new CsvRouteBuilder();
        CamelContext ctx=new DefaultCamelContext();
        try {
 		ctx.addRoutes(routeBuilder);
 		ctx.start();
 		Thread.sleep(2000);
 		CSVMain app=new CSVMain();
 		app.template = ctx.createProducerTemplate();
 		ArrayList<BookModel> books = new ArrayList<BookModel>();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-yyyy");
        BookModel book = new BookModel();
        book.setCategory("PROGRAMMING");
        book.setTitle("Camel PSRP");
        book.setTitleLanguage("en");
        book.setAuthor1("Jagan");
        book.setAuthor2("Mohan");
        book.setPublishDate(simpleDateFormat.parse("Dec-2010"));
        book.setPrice(BigDecimal.valueOf(49.99));
        books.add(book);
        book = new BookModel();
        book.setCategory("PROGRAMMING");
        book.setTitle("Apache Camel Developer's Span");
        book.setTitleLanguage("en");
        book.setAuthor1("jagan");
        book.setAuthor2("paspula");
        book.setPublishDate(simpleDateFormat.parse("Dec-2013"));
        book.setPrice(BigDecimal.valueOf(49.99));
        books.add(book);
        final String response = app.template.requestBody("direct:marshal", books, String.class);
        
        final String request = "PROGRAMMING,Camel in PSRP,en,jagan mohan,jagan,Dec-2010,49.99\n" +
                "PROGRAMMING,Apache Camel Developer's Span,en,jagan,mohan,Dec-2013,49.99\n";
            app.template.requestBody("direct:unmarshal", request, List.class);
        ctx.stop();
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }}

