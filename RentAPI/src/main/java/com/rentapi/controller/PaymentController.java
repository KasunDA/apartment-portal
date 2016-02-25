package com.rentapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.rentapi.model.CreditCardPaymentInfo;
import com.rentapi.service.PaymentService;

import net.authorize.api.contract.v1.ANetApiResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private View jsonView;
	
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/chargeCreditCard", method = RequestMethod.POST, consumes="application/json")
	public ModelAndView chargeCreditCard(@RequestBody CreditCardPaymentInfo cardInfo) {
		LOGGER.info(cardInfo.toString());
		
		ANetApiResponse response = paymentService.chargeCreditCard(cardInfo, 10.00);
		
		LOGGER.info(response.toString());
		
		return new ModelAndView(jsonView, "data", response);
	}
}
