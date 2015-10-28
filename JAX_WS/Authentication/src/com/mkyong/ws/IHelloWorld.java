package com.mkyong.ws;

import java.awt.Image;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IHelloWorld {

	@WebMethod
	public String getHellWorldAsString();
	
	@WebMethod
	public String echo(String input);
	
	@WebMethod(operationName = "retrieveImage")
	public Image retrieveImage(String name);
	
	@WebMethod(operationName = "uploadImage")
	public String uploadImage(Image file);
}
