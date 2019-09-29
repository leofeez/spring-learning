package com.leofee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        jdbcTemplate.update("insert into `order`(order_code, create_time) value ('20190724225301', now())");
    }
}
