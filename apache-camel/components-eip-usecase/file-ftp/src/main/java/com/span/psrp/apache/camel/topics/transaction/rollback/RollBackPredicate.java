package com.span.psrp.apache.camel.topics.transaction.rollback;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

public class RollBackPredicate implements Predicate{

	@Override
	public boolean matches(Exchange arg0) {
		String audit=arg0.getIn().getBody(String.class);
		return audit.contains("rollback");
	}

}
