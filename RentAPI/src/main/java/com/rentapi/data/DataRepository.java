package com.rentapi.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.ContactInfo;
import com.rentapi.model.SearchQuery;
import com.rentapi.model.SearchResultItem;

@Component
public class DataRepository {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DataRepository(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public void saveOrUpdate(ContactInfo contactInfo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO contact (firstName, lastName, emailAddress, message)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, contactInfo.getFirstName(), contactInfo.getLastName(), contactInfo.getemailAddress(),
				contactInfo.getMessage());
	}

	public List<SearchResultItem> search(SearchQuery query) {
		List<SearchResultItem> list = new ArrayList<SearchResultItem>();

		// TODO: Fill the list from database

		return list;
	}

	public ApartmentInfo getApartment(int apartmentId) {
		String sql = "SELECT * FROM rentportal.property where PropertyID = " + apartmentId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<ApartmentInfo>() {

			@Override
			public ApartmentInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ApartmentInfo apartmentModel = new ApartmentInfo();
					apartmentModel.setAptId(rs.getInt("PropertyID"));
					apartmentModel.setAptNo(rs.getString("AptNo"));
					// apartmentModel.setNo_of_Bath(rs.getInt("No_of_Bath"));
					// apartmentModel.setLocation(rs.getString("Location"));
					// apartmentModel.setState(rs.getString("State"));
					// apartmentModel.setZip(rs.getString("Zip"));
					// apartmentModel.setCity(rs.getString("City"));

					return apartmentModel;
				}

				return null;
			}
		});
	}	
}