package com.globinch.service;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback.PasswordValidationException;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.PasswordValidator;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.PlainTextPasswordRequest;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.Request;

public class MyWebServicePasswordValidator implements PasswordValidator {

	@Override
	public boolean validate(Request request) throws PasswordValidationException {
		PlainTextPasswordRequest plaintextRequest = (PlainTextPasswordRequest) request;
		// should validate from database
		if (plaintextRequest.getUsername().equals("mngo") && plaintextRequest.getPassword().equals("password")) {
			return true;
		}
		throw new PasswordValidationException("Invalid credentials provided. Authentication failed.");
	}

}
