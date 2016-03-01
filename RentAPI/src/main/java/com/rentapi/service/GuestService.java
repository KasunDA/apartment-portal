package com.rentapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentapi.data.DataRepository;
import com.rentapi.model.ApartmentInfo;
import com.rentapi.model.ContactInfo;
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
	
	public List<SearchResultItem> search(SearchQuery query){
		return this.repository.search(query);
	}
	
	public ApartmentInfo getApartment(int apartmentId){
		return this.repository.getApartment(apartmentId);
	}
	
	public void saveOrUpdate(ContactInfo contactInfo){
		this.repository.saveOrUpdate(contactInfo);
	}

}
