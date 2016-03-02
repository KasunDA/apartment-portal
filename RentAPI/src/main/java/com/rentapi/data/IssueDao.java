package com.rentapi.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;

//import org.springframework.jdbc.core.RowMapper;
//import javax.sql.DataSource;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class IssueDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> getMaintenanceIssue(final int issueID) {
		SqlParameter issueIDparam = new SqlParameter(Types.VARCHAR);

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(issueIDparam);

		final String procedureCall = "{call getMaintenanceIssue(?)}";
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection.prepareCall(procedureCall);
				callableStatement.setInt(1, issueID);
				return callableStatement;

			}
		}, paramList);
		System.out.println(resultMap);
		return resultMap;
	}

}