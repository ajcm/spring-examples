package com.example.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class JdbcAbstractRepository<T> {

    private JdbcTemplate template;


    public JdbcAbstractRepository() {
    }


    public JdbcAbstractRepository(JdbcTemplate template) {
    }

    protected JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    protected void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
}
