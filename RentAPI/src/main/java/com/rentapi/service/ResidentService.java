package com.rentapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.rentapi.model.Issue;
import com.rentapi.model.Referral;

@Component
public class ResidentService {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ResidentService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public List<Issue> GetIssues(int userId) {
		ArrayList<Issue> issues = new ArrayList<Issue>();

		String sql = "select * from rentportal.maintanance";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Issue issue = new Issue();
			issue.setDescription((String) (row.get("Description")));
			issue.setRequestDate((Date) row.get("Date_of_Request"));
			issue.setStatus((String) row.get("Status"));
			issues.add(issue);
		}

		return issues;
	}
	public List<Referral> GetReferrals(int userId) {
		ArrayList<Referral> referrals = new ArrayList<Referral>();

		String sql = "select * from rentportal.referral";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Referral referral = new Referral();
			referral.setReferralFirstName((String) (row.get("ReferralFirstName")));
			referral.setReferralLastName((String) (row.get("ReferralLastName")));
			referral.setUserId((int) row.get("UserId"));
			referral.setEmailAddress((String) row.get("EmailAddress"));
			referrals.add(referral);
		}

		return referrals;
	}

	}
