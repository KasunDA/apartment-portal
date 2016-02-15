package com.rentapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	
	@Value("${welcome-msg}")
	private String testString;
	
	public String showMessage(){
		return testString;
	}
}
