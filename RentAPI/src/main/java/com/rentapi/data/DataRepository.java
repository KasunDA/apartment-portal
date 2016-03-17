package com.rentapi.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.rentapi.model.Address;
import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.Appointment;
import com.rentapi.model.Apt;
import com.rentapi.model.AptInfo;
import com.rentapi.model.ContactInfo;
import com.rentapi.model.CreditCardPaymentInfo;
import com.rentapi.model.Guest;
import com.rentapi.model.Issue;
import com.rentapi.model.Lease;
import com.rentapi.model.LoginResponse;
import com.rentapi.model.Referral;
import com.rentapi.model.Register;
import com.rentapi.model.ResidentApplication;
import com.rentapi.model.ResidentIssue;
import com.rentapi.model.SearchQuery;
import com.rentapi.model.SearchResultItem;
import com.rentapi.model.Staff;
import com.rentapi.model.Resident;

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
			referral.setApprovedByName((String) (row.get("FirstName")) + " " + (String) (row.get("LastName")));
			referral.setPhoneNumber((String) row.get("PhoneNumber"));

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
		String sql = "call createReferral(?,?,?,?,?)";
		Integer referralId = (Integer) jdbcTemplate.queryForObject(
				sql, new Object[] { referral.getResidentId(), referral.getGuestName(),
						referral.getAddress().getAddressID(), referral.getPhoneNumber(), referral.getEmailAddress() },
				Integer.class);
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
				apartment.setRent(rs.getFloat("Rent"));
				apartment.setIsActive(rs.getBoolean("IsActive"));
				apartment.setPropertyType(rs.getInt("PropertyTypeID"));
				apartment.setPropertyTypeName(rs.getString("PropertyTypeName"));

				return apartment;
			}

		});

		return rows;
	}

	public Integer CreateApt(int userId, Apt apartment) {
		String sql = "call `createAdminProperty`(?,?,?,?,?,?,?,?,?,?)";
		Integer aptId = (Integer) jdbcTemplate.queryForObject(sql,
				new Object[] { apartment.getApartmentId(), apartment.getAptNo(), apartment.getBuildingNo(),
						apartment.getBedrooms(), apartment.getBathrooms(), apartment.getGarages(), apartment.getSft(),
						apartment.getRent(), apartment.getPropertyType(), apartment.getIsActive() },
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
				while (rs.next()) {
					apartment.setApartmentId(rs.getInt("PropertyID"));
					apartment.setAptNo(rs.getString("AptNo"));
					apartment.setBuildingNo(rs.getString("BuildingNo"));
					apartment.setBedrooms(rs.getInt("NoOfBedrooms"));
					apartment.setBathrooms(rs.getInt("NoOfBathrooms"));
					apartment.setGarages(rs.getInt("Garage"));
					apartment.setSft(rs.getInt("Sqft"));
					apartment.setIsActive(rs.getBoolean("IsActive"));
					apartment.setPropertyType(rs.getInt("PropertyTypeID"));
					apartment.setPropertyTypeName(rs.getString("PropertyTypeName"));
				}

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

	public List<String> GetAppointmentTimes(LocalDate requestDate) {
		String sql = "call `getAppointmentTimes`(?)";
		Date d = null;
		if (requestDate != null) {
			d = requestDate.toDate();
			LOGGER.info(d.toString());
		}

		List<String> times = jdbcTemplate.query(sql, new Object[] { d }, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				// System.out.println(String.format("%s",
				// rs.getInt("AppointmentDateStart")));
				SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");

				Timestamp d = rs.getTimestamp("AppointmentDateStart");
				System.out.println(d.toString());
				return (d != null) ? sdf.format(d) : null;
			}

		});
		return times;
	}

	public Integer ScheduleAppointment(Appointment appointment) {
		String sql = "call `scheduleAppointment`(?,?,?,?)";
		Integer appointmentId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] { appointment.getName(),
				appointment.getPhone(), appointment.getEmail(), appointment.getAppointmentDate() }, Integer.class);
		return appointmentId;
	}

	public LoginResponse ValidateUser(String userName, String password) {
		String sql = "call rentportal.validateUser(?,?)";
		LoginResponse response = jdbcTemplate.queryForObject(sql, new Object[] { userName, password },
				new RowMapper<LoginResponse>() {
					@Override
					public LoginResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
						LoginResponse item = new LoginResponse();
						item.setError(rs.getString("ErrorMsg"));
						item.setSession(rs.getString("SessionGUID"));
						item.setAccountID(rs.getInt("AccountID"));
						item.setResidentID(rs.getInt("ResidentID"));
						item.setStaffID(rs.getInt("StaffID"));
						item.setIsAdmin(rs.getBoolean("IsAdmin"));
						item.setIsGuest(rs.getBoolean("IsGuest"));
						return item;
					}
				});

		return response;
	}

	public Integer SaveAddress(Address address) {
		Integer addressId = address.getAddressID();
		if (addressId == null || addressId <= 0) {
			String sql = "call `saveAddress`(?,?,?,?,?,?,?)";
			addressId = (Integer) jdbcTemplate.queryForObject(sql, new Object[] { null, address.getAddress1(),
					address.getAddress2(), address.getCity(), address.getState(), address.getZip(), "USA" },
					Integer.class);
		}
		return addressId;
	}

	// public Integer CreateMaintenance(int residentId, Maintenance maintenance)
	// {
	// String sql = "call `createAdminIssue`(?,?,?,?)";
	// Integer issueId = (Integer) jdbcTemplate.queryForObject(sql, new Object[]
	// { issue.getResidentID(),
	// issue.getTitle(), issue.getDescription(), issue.getStaffID(),
	// issue.getMaintenanceStatusCodeID() },
	// Integer.class);
	// return issueId;
	// }

	public void saveOrUpdate(ResidentApplication residentApplication) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO residentApplication (currentAddress, currentAddressStartDate, "
				+ "currentAddressEndDate, previousAddress, employerName, companyAddress, annualIncome)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, residentApplication.getCurrentAddress(),
				residentApplication.getCurrentAddressStartDate(), residentApplication.getCurrentAddressEndDate(),
				residentApplication.getPreviousAddress(), residentApplication.getEmployerName(),
				residentApplication.getCompanyAddress(), residentApplication.getAnnualIncome());
	}

	public List<Resident> GetAdminResidentList() {

		String sql = "call getAdminResidentList();";

		List<Resident> rows = jdbcTemplate.query(sql, new RowMapper<Resident>() {

			@Override
			public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resident r = new Resident();

				r.setFirstName(rs.getString("FirstName"));
				r.setLastName(rs.getString("LastName"));
				r.setEmail(rs.getString("Email"));
				r.setPhoneNumber(rs.getString("PhoneNumber"));
				r.setLeaseStartDate(rs.getDate("LeaseStartDate"));
				r.setLeaseEndDate(rs.getDate("LeaseEndDate"));
				r.setResidentID(rs.getInt("ResidentID"));
				r.setAccountID(rs.getInt("AccountID"));
				r.setPropertyID(rs.getInt("PropertyID"));
				r.setLeaseID(rs.getInt("LeaseID"));

				return r;
			}
		});

		return rows;
	}

	public void setupResidentLease(Resident resident) {
		String sql = "call createAdminLease(?,?,?,?)";
		jdbcTemplate.update(sql, resident.getPropertyID(), resident.getResidentID(), resident.getLeaseStartDate(),
				resident.getLeaseEndDate());
	}

	public Resident GetAdminResident(int residentId) {

		String sql = "call getAdminResident(?);";

		Resident rows = jdbcTemplate.queryForObject(sql, new Object[] { residentId }, new RowMapper<Resident>() {

			@Override
			public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resident r = new Resident();

				r.setFirstName(rs.getString("FirstName"));
				r.setLastName(rs.getString("LastName"));
				r.setEmail(rs.getString("Email"));
				r.setPhoneNumber(rs.getString("PhoneNumber"));
				r.setLeaseStartDate(rs.getDate("LeaseStartDate"));
				r.setLeaseEndDate(rs.getDate("LeaseEndDate"));
				r.setResidentID(rs.getInt("ResidentID"));
				r.setAccountID(rs.getInt("AccountID"));
				r.setPropertyID(rs.getInt("PropertyID"));
				r.setLeaseID(rs.getInt("LeaseID"));
				r.setIsActive(rs.getBoolean("IsActive"));

				return r;
			}
		});

		return rows;
	}

	public List<Staff> GetStaffList() {

		String sql = "call getStaffList();";

		List<Staff> rows = jdbcTemplate.query(sql, new RowMapper<Staff>() {

			@Override
			public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
				Staff r = new Staff();

				r.setFirstName(rs.getString("FirstName"));
				r.setLastName(rs.getString("LastName"));
				r.setStaffId(rs.getInt("StaffID"));

				return r;
			}
		});

		return rows;
	}

	public Boolean updateIssue(Issue issue) {
		String sql = "call `updateIssue`(?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { issue.getMaintenanceID(), issue.getStaffID(), issue.getClosedDate(),
				issue.getMaintenanceStatusCodeID() });
		return true;

	}

	public void createAccount(Register account) {
		String sql = "call `createAccount`(?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { account.getFirstName(), account.getLastName(), account.getUsername(),
				account.getPassword(), account.getEmail() });
	}

	public List<Guest> getAdminGuests() {

		String sql = "call getAdminGuests();";

		List<Guest> rows = jdbcTemplate.query(sql, new RowMapper<Guest>() {

			@Override
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				Guest r = new Guest();

				r.setGuestName(rs.getString("FirstName") + " " + rs.getString("LastName"));
				r.setGuestId(rs.getInt("ResidentID"));

				return r;
			}
		});

		return rows;

	}

	public List<AptInfo> getAdminAvailablePropertyList() {

		String sql = "call getAdminAvailablePropertyList();";

		List<AptInfo> rows = jdbcTemplate.query(sql, new RowMapper<AptInfo>() {

			@Override
			public AptInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AptInfo r = new AptInfo();

				r.setDescription(rs.getString("AptNo") + " " + rs.getString("BuildingNo"));
				r.setApartmentId(rs.getInt("PropertyID"));

				return r;
			}
		});

		return rows;
	}

	public List<Appointment> getAdminAppointments(String requestDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {

			if (requestDate != null)
				date = sdf.parse(requestDate);
		} catch (ParseException e) {
		}
		
		if (date == null)
			date = new Date();

		String sql = "call getAdminAppointments(?);";

		List<Appointment> rows = jdbcTemplate.query(sql, new Object[] { date }, new RowMapper<Appointment>() {

			@Override
			public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Appointment r = new Appointment();

				r.setName(rs.getString("Name"));
				r.setPhone(rs.getString("Phone"));
				r.setEmail(rs.getString("EmailAddress"));
				r.setAppointmentDate(rs.getTimestamp("AppointmentDateStart"));

				return r;
			}
		});

		return rows;
	}

}
