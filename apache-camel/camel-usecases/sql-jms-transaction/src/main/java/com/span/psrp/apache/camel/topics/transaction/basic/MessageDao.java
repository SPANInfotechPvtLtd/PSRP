package com.span.psrp.apache.camel.topics.transaction.basic;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Utility class for accessing the <code>messages</code> table used in the database examples.
 */
public class MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public MessageDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getMessageCount(String message) {
        return jdbcTemplate.queryForInt("select count(*) from messages where message = ?", message);
    }
}
