package com.span.psrp.apache.camel.topics.transaction.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.camel.Exchange;

public class AuditLogHelper {

	private Random ran = new Random();

    /**
     * Generates a new topic structured as a {@link Map}
     */
    public Map<String, Object> generateNewTopic(Exchange ex){
    	System.out.print("enterd new Topic"+"generateNewTopic"+"\n");
    	AuditLog audit=ex.getIn().getBody(AuditLog.class);
        Map<String, Object> answer = new HashMap<String, Object>();
         answer.put("auditid", new Integer(ran.nextInt()));
        answer.put("auditUser", audit.getMessage());
        return answer;
    }
    
    public Map<String, Object> generateNewTopic1(Exchange ex){
    	System.out.print("enterd new Topic"+"generateNewTopic"+"\n");
    	Map<String, Object> answer1 =ex.getIn().getBody(Map.class);
    	System.out.print("enterd new Topic"+"generateNewTopic"+"\n");
        Map<String, Object> answer = new HashMap<String, Object>();
         answer.put("auditid", new Integer(ran.nextInt()));
        answer.put("auditUser", answer1.get("auditUser"));
        return answer;
    }
}
