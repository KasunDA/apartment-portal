package com.rentapi.service;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentapi.data.DataRepository;
import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.Appointment;
import com.rentapi.model.ContactInfo;
import com.rentapi.model.ResidentApplication;
import com.rentapi.model.SearchQuery;
import com.rentapi.model.SearchResultItem;

@Component
public class GuestService {

	private DataRepository repository; // creating data access layer

	// For GuestService constructor

	// we are injecting(Autowired) data repository
	// object repository.
	@Autowired
	public GuestService(DataRepository repository) {
		this.repository = repository;
	}

	public List<SearchResultItem> search(SearchQuery query) {
		return this.repository.search(query);
	}

	public ApartmentInfo getApartment(int apartmentId, int residentId) {
		return this.repository.getApartment(apartmentId, residentId);
	}

	public void saveOrUpdate(ContactInfo contactInfo) {
		this.repository.saveOrUpdate(contactInfo);
	}

	public List<DateTime> GetAppointmentTimes(LocalDate requestDate) {
		List<String> times = repository.GetAppointmentTimes(requestDate);
		List<DateTime> availableDateTimes = new ArrayList<DateTime>();

		DateTime startDateTime = new DateTime(requestDate.getYear(), requestDate.getMonthOfYear(),
				requestDate.getDayOfMonth(), 9, 0);

		for (int i = 0; i < 18; i++) {
			// Search booked appointments
			Boolean appointmentFound = false;
			for (int j = 0; j < times.size(); j++) {

				DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-M-yyyy HH:mm:ss");
				DateTime dt = times.get(j) == null ? null : formatter.parseDateTime(times.get(j));
				
				System.out.println("times.get(j)");
				System.out.println(times.get(j));
				System.out.println("startDateTime");				
				System.out.println(startDateTime);
				
				if (dt.getHourOfDay() == startDateTime.getHourOfDay()
						&& dt.getMinuteOfHour() == startDateTime.getMinuteOfHour()) {
					appointmentFound = true;
					break;
				}
			}

			if (!appointmentFound)
				availableDateTimes.add(startDateTime);

			startDateTime = startDateTime.plusMinutes(30);
		}

		return availableDateTimes;
	}

	public Integer ScheduleAppointment(Appointment appointment) {
		return this.repository.ScheduleAppointment(appointment);
	}

	public Boolean bookmarkApartment(Integer aptId, int residentId, Boolean bookmarked) {
		return this.repository.bookmarkApartment(aptId, residentId, bookmarked);
	}

	public void saveOrUpdate(ResidentApplication residentApplication) {
		this.repository.saveOrUpdate(residentApplication);
	}

}
