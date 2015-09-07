package com.span.psrp.apache.camel.topics.sql;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NewTopicBean {
	
	private Random ran = new Random();

    /**
     * Generates a new topic structured as a {@link Map}
     */
    public Map<String, Object> generateNewTopic() {
    	System.out.print("enterd new Topic"+"generateNewTopic"+"\n");
        Map<String, Object> answer = new HashMap<String, Object>();
         answer.put("TopicId", new Integer(ran.nextInt()));
        answer.put("TopicName", "Camel in Action");
        answer.put("url",  "Camel in Action" );
        answer.put("ModuleId", new Integer(ran.nextInt()));
        answer.put("CreateDate", new Date(0));
        return answer;
    }

    /**
     * Processes the NewTopic
     * @param data  the NewTopic as a {@link Map}
     * @return the transformed NewTopic
     */
    public String processNewTopic(Map<String, Object> data) {
        return "Processed NewTopic id " + data.get("TopicId") + " TopicName " 
		+ data.get("TopicName")
		+ " of " + data.get("ModuleId") + " copies of " + data.get("url");
    }

}
