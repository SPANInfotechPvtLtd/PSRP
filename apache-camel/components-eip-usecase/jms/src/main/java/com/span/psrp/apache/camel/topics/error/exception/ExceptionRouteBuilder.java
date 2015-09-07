package com.span.psrp.apache.camel.topics.error.exception;

import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.error.util.FlakyException;
import com.span.psrp.apache.camel.topics.error.util.FlakyProcessor;
import com.span.psrp.apache.camel.topics.error.util.SporadicException;

public class ExceptionRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(FlakyException.class, SporadicException.class).to(
				"direct:error");
		onException(Exception.class).to("mock:genericerror");
		from("direct:start").bean(FlakyProcessor.class).end();
		from("direct:handled").onException(FlakyException.class).handled(true)
				.transform(constant("Something Bad Happened!"))
				.to("mock:error").end().bean(FlakyProcessor.class)
				.transform(constant("All Good")).to("mock:result");
		from("direct:continue").onException(FlakyException.class)
				.continued(true).to("mock:ignore").end()
				.bean(FlakyProcessor.class).transform(constant("All Good"))
				.to("mock:result");
		from("direct:error").onException(FlakyException.class).handled(true)
		.end().bean(FlakyProcessor.class);
	}

}
