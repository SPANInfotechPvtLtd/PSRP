package com.span.psrp.apache.camel.topics.transformation.enrich;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class EnrichWithAggregatorRouteBuilder extends RouteBuilder {
	
    private MergeInReplacementText myMerger;

    @Override
    public void configure() throws Exception {
    	from("direct:expander")
        .bean(AbbreviationExpander.class, "expand");

        from("direct:start")
            .bean(myMerger, "setup").enrich("direct:expander", (AggregationStrategy) myMerger);
    }

    public MergeInReplacementText getMyMerger() {
        return myMerger;
    }

    public void setMyMerger(MergeInReplacementText myMerger) {
        this.myMerger = myMerger;
    }

}
