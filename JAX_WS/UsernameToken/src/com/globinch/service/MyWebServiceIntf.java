package com.globinch.service;

import java.awt.Image;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="MyWebService")
public interface MyWebServiceIntf {
	/** * Web service operation */
	@WebMethod(operationName = "greetCustomer")
	public String greetCustomer(@WebParam(name = "parameter") String parameter);

	@WebMethod(operationName = "retrieveImage")
	public Image retrieveImage(@WebParam(name = "name") String name);
	
	@WebMethod(operationName = "uploadImage")
	public String uploadImage(@WebParam(name = "file") Image file, @WebParam(name = "name") String name);
}
