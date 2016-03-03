package com.rentapi.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rentapi.data.DataRepository;
import com.rentapi.model.CreditCardPaymentInfo;
import com.rentapi.model.PaymentResponse;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ANetApiResponse;
import net.authorize.api.contract.v1.CreateTransactionRequest;
import net.authorize.api.contract.v1.CreateTransactionResponse;
import net.authorize.api.contract.v1.CreditCardType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.MessagesType.Message;
import net.authorize.api.contract.v1.PaymentType;
import net.authorize.api.contract.v1.TransactionRequestType;
import net.authorize.api.contract.v1.TransactionResponse;
import net.authorize.api.contract.v1.TransactionTypeEnum;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;

@Component
public class PaymentService {

	@Value("${apiLoginId}")
	private String apiLoginId;

	@Value("${transactionKey}")
	private String transactionKey;

	private DataRepository repository; // creating data access layer
	
	@Autowired
	public PaymentService(DataRepository repository) {
		this.repository = repository;
	}

	public PaymentResponse processPayment(CreditCardPaymentInfo info, Double amount) {		
		Integer paymentInfoID = this.SavePaymentInfo(info);
		ANetApiResponse response = this.chargeCreditCard(info, amount);

		String responseCode = null, transactionNumber = null, refNo = null;//, authCode;
		MessageTypeEnum resultCode;
		List<String> messages = new ArrayList<String>();

		if (response != null) {
			resultCode = response.getMessages().getResultCode();
			refNo = response.getRefId();
			if (resultCode == MessageTypeEnum.OK) {
				TransactionResponse result = ((CreateTransactionResponse) response).getTransactionResponse();
				responseCode = result.getResponseCode();
				if (responseCode.equals("1")) {
					responseCode = result.getResponseCode();
					//authCode = result.getAuthCode();
					transactionNumber = result.getTransId();
				}
			}

			for (Message msg : response.getMessages().getMessage()) {
				messages.add(msg.getCode() + " - " + msg.getText());
			}
		}

		String pmtMessage = "", txnCode;
		Integer paymentStatus = 0;
		if (responseCode == "1")
			paymentStatus = 1;	// Success
		else
			paymentStatus = 2;	// Failure
		
		for(String msg: messages)
			pmtMessage += (msg + "\r\n");
				
		UUID guid = java.util.UUID.randomUUID();
		txnCode = guid.toString();

		repository.SavePaymentTxn(paymentInfoID, paymentStatus, pmtMessage, amount, txnCode, refNo);

		PaymentResponse pmtResponse = new PaymentResponse();
		if (responseCode == "1") {
			pmtResponse.setHasError(false);
			pmtResponse.setTransactionNumber(txnCode);
		} else {
			pmtResponse.setHasError(true);
			pmtResponse.setMessages(messages);
			pmtResponse.setTransactionNumber(txnCode);
		}

		return pmtResponse;
	}

	public ANetApiResponse chargeCreditCard(CreditCardPaymentInfo info, Double amount) {

		// Common code to set for all requests
		ApiOperationBase.setEnvironment(Environment.SANDBOX);

		MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
		merchantAuthenticationType.setName(apiLoginId);
		merchantAuthenticationType.setTransactionKey(transactionKey);
		ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

		// Populate the payment data
		PaymentType paymentType = new PaymentType();
		CreditCardType creditCard = new CreditCardType();
		creditCard.setCardNumber(info.getCardNumber());
		creditCard.setExpirationDate(info.getExpirationDate());
		paymentType.setCreditCard(creditCard);

		// Create the payment transaction request
		TransactionRequestType txnRequest = new TransactionRequestType();
		txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
		txnRequest.setPayment(paymentType);
		txnRequest.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));

		// Make the API Request
		CreateTransactionRequest apiRequest = new CreateTransactionRequest();
		apiRequest.setTransactionRequest(txnRequest);
		CreateTransactionController controller = new CreateTransactionController(apiRequest);
		controller.execute();

		CreateTransactionResponse response = controller.getApiResponse();

		return response;

	}

	public Integer SavePaymentInfo(CreditCardPaymentInfo info) {
		return repository.SavePaymentInfo(info);
	}
}
