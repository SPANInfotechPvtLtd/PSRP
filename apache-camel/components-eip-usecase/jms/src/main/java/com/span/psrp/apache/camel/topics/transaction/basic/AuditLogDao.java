package com.span.psrp.apache.camel.topics.transaction.basic;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Utility class for accessing the <code>audit_log</code> table used in the database examples.
 */
public class AuditLogDao {

    private final JdbcTemplate jdbcTemplate;

    public AuditLogDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getAuditCount(String message) {
        return jdbcTemplate.queryForInt("select count(*) from audit_log where message = ?", message);
    }
}
