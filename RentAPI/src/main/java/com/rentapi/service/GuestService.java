package com.rentapi.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class GuestService {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GuestService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public void Search() {
	}
}
