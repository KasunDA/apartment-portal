package com.rentapi.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.rentapi.controller.ResidentController;
import com.rentapi.model.Address;
import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.Appointment;
import com.rentapi.model.Apt;
import com.rentapi.model.ContactInfo;
import com.rentapi.model.CreditCardPaymentInfo;
import com.rentapi.model.Issue;
import com.rentapi.model.Lease;
import com.rentapi.model.Referral;
import com.rentapi.model.ResidentIssue;
import com.rentapi.model.SearchQuery;
import com.rentapi.model.SearchResultItem;

@Component
public class DataRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataRepository.class);

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
		String sql = "call `searchApartments`(?,?,?)";

		List<SearchResultItem> row = jdbcTemplate.query(sql,
				new Object[] { query.getNoOfBedrooms(), query.getNoOfBathrooms(), query.getGarages() },
				new RowMapper<SearchResultItem>() {
					@Override
					public SearchResultItem mapRow(ResultSet rs, int rowNum) throws SQLException {
						SearchResultItem item = new SearchResultItem();
						item.setNoOfBathrooms(rs.getInt("NoOfBathrooms"));
						item.setNoOfBedrooms(rs.getInt("NoOfBedrooms"));
						item.setSft(rs.getInt("Sqft"));
						item.setGarages(rs.getInt("Garage"));
						item.setAptNo(rs.getString("AptNo"));
						item.setBuildingNo(rs.getString("BuildingNo"));
						item.setRent(rs.getFloat("Rent"));
						item.setAptId(rs.getInt("PropertyID"));
						return item;
					}
				});
		return row;
	}

	public ApartmentInfo getApartment(int apartmentId, int residentId) {
		String sql = "call getProperty(?,?);";

		ApartmentInfo row = jdbcTemplate.queryForObject(sql, new Object[] { apartmentId, residentId },
				new RowMapper<ApartmentInfo>() {

					@Override
					public ApartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						ApartmentInfo apartment = new ApartmentInfo();

						apartment.setAptId(rs.getInt("PropertyID"));
						apartment.setAptNo(rs.getString("AptNo"));
						apartment.setBuildingNo(rs.getString("BuildingNo"));
						apartment.setNoOfBedrooms(rs.getInt("NoOfBedrooms"));
						apartment.setNoOfBathrooms(rs.getInt("NoOfBathrooms"));
						apartment.setGarage(rs.getInt("Garage"));
						apartment.setSqft(rs.getInt("Sqft"));
						apartment.setPropertyTypeName(rs.getString("PropertyTypeName"));
						apartment.setBookmarked(rs.getBoolean("Bookmark"));
						apartment.setRent(rs.getFloat("Rent"));

						return apartment;
					}

				});

		return row;
	}

	public Issue getIssue(int issueId) {
		return null;
	}

	public List<Issue> GetIssues(int userId) {
		ArrayList<Issue> issues = new ArrayList<Issue>();

		String sql = "select * from rentportal.maintenance";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Issue issue = new Issue();
			issue.setDescription((String) (row.get("Description")));
			// issue.setResidentID((Integer) row.get("ResidentID"));
			// issue.setTitle((String) row.get("Title"));
			issues.add(issue);
		}

		return issues;
	}

	public ResidentIssue GetResidentIssue(int issueId, int residentId) {

		String sql = "call `getMaintenanceIssue`(?,?)";

		ResidentIssue row = jdbcTemplate.queryForObject(sql, new Object[] { issueId, residentId },
				new RowMapper<ResidentIssue>() {

					@Override
					public ResidentIssue mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResidentIssue residentissue = new ResidentIssue();

						residentissue.setResidentIssuesID(rs.getInt("ResidentIssuesID"));
						residentissue.setMaintenanceID(rs.getInt("MaintenanceID"));
						residentissue.setResidentID(rs.getInt("ResidentID"));

						return residentissue;
					}
				});
		return row;
	}

	public Integer CreateIssue(ResidentIssue issue) {
		String sql = "call `createIssue`(?,?,?)";
		Integer issueId = (Integer) jdbcTemplate.queryForObject(sql,
				new Object[] { issue.getResidentID(), issue.getTitle(), issue.getDescription() }, Integer.class);
		return issueId;
	}

	public List<Referral> GetReferrals(int residentId) {
		ArrayList<Referral> referrals = new ArrayList<Referral>();

		String sql = "call `getReferralsList`(?);";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] { residentId });
		for (Map<String, Object> row : rows) {
			Referral referral = new Referral();
			referral.setGuestName((String) (row.get("GuestName")));
			referral.setEmailAddress((String) row.get("EmailAddress"));
			referral.setResidentId((Integer) (row.get("ResidentID")));
			referral.setApprovedDate((Date) (row.get("ApprovedDate")));
			referral.setApprovedBy((Integer) (row.get("ApprovedBy")));
			referral.setApprovedByName((String) (row.get("ApprovedByName")));

			Address address = new Address();
			address.setAddress1((String) (row.get("Address1")));
			address.setAddress2((String) (row.get("Address2")));
			address.setCity((String) (row.get("City")));
			address.setState((String) (row.get("State")));
			address.setZip((String) (row.get("Zip")));

			referral.setAddress(address);

			referrals.add(referral);
		}

		return referrals;
	}

	public Integer CreateReferral(Referral referral) {
		String sql = "call createReferral()";
		Integer referralId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
		return referralId;
	}

	public List<Apt> GetAptList(Boolean isActive) {

		String sql = "call getAdminPropertyList(?);";

		List<Apt> rows = jdbcTemplate.query(sql, new Object[] { isActive }, new RowMapper<Apt>() {

			@Override
			public Apt mapRow(ResultSet rs, int rowNum) throws SQLException {
				Apt apartment = new Apt();

				apartment.setApartmentId(rs.getInt("PropertyID"));
				apartment.setAptNo(rs.getString("AptNo"));
				apartment.setBuildingNo(rs.getString("BuildingNo"));
				apartment.setBedrooms(rs.getInt("NoOfBedrooms"));
				apartment.setBathrooms(rs.getInt("NoOfBathrooms"));
				apartment.setGarages(rs.getInt("Garage"));
				apartment.setSft(rs.getInt("Sqft"));
				apartment.setIsActive(rs.getBoolean("IsActive"));
				apartment.setPropertyTypeName(rs.getString("PropertyTypeName"));

				return apartment;
			}

		});

		return rows;
	}

	public Integer CreateApt(int userId, Apt apartment) {
		String sql = "call `createAdminProperty`(?,?,?,?,?,?,?)";
		Integer aptId = (Integer) jdbcTemplate.queryForObject(sql,
				new Object[] { apartment.getAptNo(), apartment.getBuildingNo(), apartment.getBedrooms(),
						apartment.getBathrooms(), apartment.getGarages(), apartment.getSft(),
						apartment.getPropertyType() },
				Integer.class);
		return aptId;
	}

	public void RemoveApt(int userId, int apartmentId) {
		String sql = "call `removeAdminProperty`(?)";
		jdbcTemplate.update(sql, new Object[] { apartmentId });
	}

	public Apt GetApt(Integer apartmentId) {

		String sql = "call getAdminProperty(?);";

		Apt row = jdbcTemplate.query(sql, new Object[] { apartmentId }, new ResultSetExtractor<Apt>() {

			@Override
			public Apt extractData(ResultSet rs) throws SQLException {
				Apt apartment = new Apt();

				apartment.setApartmentId(rs.getInt("PropertyID"));
				apartment.setAptNo(rs.getString("AptNo"));
				apartment.setBuildingNo(rs.getString("BuildingNo"));
				apartment.setBedrooms(rs.getInt("NoOfBedrooms"));
				apartment.setBathrooms(rs.getInt("NoOfBathrooms"));
				apartment.setGarages(rs.getInt("Garage"));
				apartment.setSft(rs.getInt("Sqft"));
				apartment.setIsActive(rs.getBoolean("IsActive"));
				apartment.setPropertyTypeName(rs.getString("PropertyTypeName"));

				return apartment;
			}

		});

		return row;
	}

	public Integer CreateLease(int userId, Lease lease) {
		String sql = "call `createAdminProperty`(?,?,?,?)";
		Integer leaseId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] { lease.getLeaseId(),
				lease.getLeaseStartDate(), lease.getLeaseEndDate(), lease.getLeaseCreateDate() }, Integer.class);
		return leaseId;
	}

	public List<Lease> GetAdminLeaseList() {

		String sql = "call getAdminLeaseList();";

		List<Lease> rows = jdbcTemplate.query(sql, new RowMapper<Lease>() {

			@Override
			public Lease mapRow(ResultSet rs, int rowNum) throws SQLException {
				Lease lease = new Lease();

				lease.setLeaseId(rs.getInt("LeaseID"));
				lease.setLeaseStartDate(rs.getDate("LeaseStartDate"));
				lease.setLeaseEndDate(rs.getDate("LeaseEndDate"));
				lease.setLeaseCreateDate(rs.getDate("LeaseCreateDate"));
				lease.setResidentFirstName(rs.getString("FirstName"));
				lease.setResidentLastName(rs.getString("LastName"));
				lease.setResidentId(rs.getInt("ResidentID"));

				return lease;
			}

		});

		return rows;
	}

	public List<Referral> getAdminReferralsList() {
		String sql = "call `getAdminReferralsList`();";

		List<Referral> rows = jdbcTemplate.query(sql, new RowMapper<Referral>() {

			@Override
			public Referral mapRow(ResultSet rs, int rowNum) throws SQLException {
				Referral referral = new Referral();
				referral.setReferralId(rs.getInt("ResidentID"));
				referral.setGuestName(rs.getString("GuestName"));
				referral.setEmailAddress(rs.getString("EmailAddress"));
				referral.setResidentName(rs.getString("FirstName") + " " + rs.getString("LastName"));
				referral.setResidentId((rs.getInt("ResidentID")));
				referral.setApprovedDate(rs.getDate("ApprovedDate"));
				referral.setApprovedBy(rs.getInt("ApprovedBy"));

				String staffFirstName = rs.getString("StaffFirstName");
				String staffLastName = rs.getString("StaffLastName");

				referral.setApprovedByName(String.format("%s %s", staffFirstName == null ? "" : staffFirstName,
						staffLastName == null ? "" : staffLastName));

				Address address = new Address();
				address.setAddress1(rs.getString("Address1"));
				address.setAddress2(rs.getString("Address2"));
				address.setCity(rs.getString("City"));
				address.setState(rs.getString("State"));
				address.setZip(rs.getString("Zip"));

				referral.setAddress(address);

				return referral;
			}
		});

		return rows;
	}

	public List<Issue> getAdminIssueList() {

		String sql = "call getAdminIssueList();";

		List<Issue> rows = jdbcTemplate.query(sql, new RowMapper<Issue>() {

			@Override
			public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
				Issue issue = new Issue();

				issue.setDescription(rs.getString("Description"));
				issue.setMaintenanceID(rs.getInt("MaintenanceID"));
				issue.setResidentID(rs.getInt("AccountID"));
				issue.setCreateDate(rs.getDate("CreateDate"));
				issue.setClosedDate(rs.getDate("ClosedDate"));
				issue.setStaffID(rs.getInt("StaffID"));
				issue.setMaintenanceStatusCodeID(rs.getInt("MaintenanceStatusCodeID"));
				issue.setStatusName(rs.getString("StatusName"));
				issue.setFirstName(rs.getString("FirstName"));
				issue.setLastName(rs.getString("LastName"));
				issue.setStaffFirstName(rs.getString("StaffFirstName"));
				issue.setStaffLastName(rs.getString("StaffLastName"));
				return issue;
			}
		});

		return rows;
	}

	public Integer CreateAdminIssue(int userId, Issue issue) {
		String sql = "call `createAdminIssue`(?,?,?,?)";
		Integer issueId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] { issue.getResidentID(),
				issue.getTitle(), issue.getDescription(), issue.getStaffID(), issue.getMaintenanceStatusCodeID() },
				Integer.class);
		return issueId;
	}

	public Boolean ApproveAdminReferral(Integer staffId, Integer referralId) {
		String sql = "call `approveAdminReferral`(?,?)";
		jdbcTemplate.update(sql, new Object[] { staffId, referralId });
		return true;
	}

	public Boolean bookmarkApartment(Integer aptId, int residentId, Boolean bookmarked) {
		String sql = "call `bookmarkApt`(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { aptId, residentId, bookmarked });
		return true;
	}

	public Integer SavePaymentInfo(CreditCardPaymentInfo info) {

		Address address = info.getBillingAddress();
		Integer addressId = info.getBillingAddress().getAddressID();
		if (addressId == null || addressId <= 0) {
			String sql = "call `saveAddress`(?,?,?,?,?,?,?)";
			addressId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] { null, address.getAddress1(),
					address.getAddress2(), address.getCity(), address.getState(), address.getZip(), "USA" },
					Integer.class);
		}

		String sql = "call `savePaymentInfo`(?,?,?,?,?,?,?,?,?)";
		Integer paymentInfoID = (Integer) jdbcTemplate
				.queryForObject(
						sql, new Object[] { info.getName(), info.getPhone(), info.getCardNumber(),
								info.getExpirationDate(), info.getSecurityCode(), addressId, null, null, null },
						Integer.class);
		return paymentInfoID;
	}

	public Integer SavePaymentTxn(int paymentInfoId, int paymentStatus, String message, Double amount, String txnCode,
			String refNo) {
		String sql = "call `savePaymentTxn`(?,?,?,?,?,?)";
		Integer paymentTransactionID = (Integer) jdbcTemplate.queryForObject(sql,
				new Object[] { paymentInfoId, paymentStatus, message, amount, txnCode, refNo }, Integer.class);
		return paymentTransactionID;
	}

	@SuppressWarnings("deprecation")
	public List<Date> GetAppointmentTimes(LocalDate requestDate) {
		String sql = "call `getAppointmentTimes`(?)";
		Date d = null;
		if (requestDate != null) {
			d = requestDate.toDate();
			LOGGER.info(d.toString());
		}
		
		List<Date> times = jdbcTemplate.query(sql, new Object[] { d }, new RowMapper<Date>() {

			@Override
			public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getDate("AppointmentDateStart");
			}

		});
		return times;
	}

	public Integer ScheduleAppointment(Appointment appointment) {
		String sql = "call `scheduleAppointment`(?,?,?,?,?,?)";
		Integer appointmentId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
		return appointmentId;
	}
}
