package com.span.psrp.apache.camel.topics.oncompletion;

import org.apache.camel.builder.RouteBuilder;

import com.span.psrp.apache.camel.topics.predicate.LogProcessor;

public class OnCompletionRouteBuilder extends RouteBuilder {

	@Override
    public void configure() throws Exception {
      /* onCompletion()
            .log("global onCompletion thread: ${threadName}")
            .to("direct:onCompletiontest1").delay(0);*/

        from("direct:onCompletion")
            .onCompletion()
                .log("onCompletion triggered: ${threadName}")
                .to("direct:onCompletiontest")
            .end()
            .log("Processing message: ${threadName}");

        from("direct:noOnCompletion")
            .log("Original thread: ${threadName}")
            .choice()
                .when(simple("${body} contains 'explode'"))
                    .throwException(new IllegalArgumentException("Exchange caused explosion"))
            .endChoice();

        from("direct:onCompletionFailure")
            .onCompletion().onFailureOnly()
                .log("onFailureOnly thread: ${threadName}")
                .to("mock:failed")
            .end()
            .log("Original thread: ${threadName}")
            .choice()
                .when(simple("${body} contains 'explode'"))
                    .throwException(new IllegalArgumentException("Exchange caused explosion"))
            .endChoice();

        from("direct:onCompletionFailureConditional")
            .onCompletion()
                    .onFailureOnly()
                    .onWhen(simple("${exception.class} == 'java.lang.IllegalArgumentException'"))
                .log("onFailureOnly thread: ${threadName}: ${exception.class}")
                .to("mock:failed")
            .end()
            .log("Original thread: ${threadName}")
            .choice()
                .when(simple("${body} contains 'explode'"))
                    .throwException(new IllegalArgumentException("Exchange caused explosion"))
            .endChoice();

        from("direct:chained")
            .log("chained")
            .onCompletion().onCompleteOnly()
                .log("onCompleteOnly thread: ${threadName}")
                .to("mock:completed")
            .end()
            .to("direct:onCompletionFailure"); // calls out to route with onCompletion set

        from("direct:onCompletionChoice")
            .onCompletion()
                .to("direct:processCompletion")
            .end()
            .log("Original thread: ${threadName}")
            .choice()
                .when(simple("${body} contains 'explode'"))
                .throwException(new IllegalArgumentException("Exchange caused explosion"))
            .endChoice();

        from("direct:processCompletion")
            .log("onCompletion thread: ${threadName}")
            .log("${body}")
            .choice()
                //.when(simple("${exception.class} == null"))
                // Since CAMEL-7707 we cannot get exception here, but onWhen still work
                .when(simple("${body} contains 'complete'"))
                    .to("mock:completed")
                .otherwise()
                    .to("mock:failed")
            .endChoice();
        
        from("direct:inAnotherRouteBuilder")
        .log("No global onCompletion should apply")
        .to("mock:outAnotherRouteBuilder");
        
        from("direct:onCompletiontest")
        .log("onCompletion thread: ${threadName}")
        .log("${body}")
        .process(new LogProcessor())
                .to("mock:completed");
        
       
    }
}
