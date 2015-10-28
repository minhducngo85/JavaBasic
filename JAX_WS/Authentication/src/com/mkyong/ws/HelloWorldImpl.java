package com.mkyong.ws;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;

/**
 * the web services implementation
 * 
 * @author mngo
 * 
 */
@WebService(endpointInterface = "com.mkyong.ws.IHelloWorld")
public class HelloWorldImpl implements IHelloWorld {

	private final static String PATH = "E:\\mtomtest\\server\\";

	@Resource
	WebServiceContext wsContext;

	/**
	 * to authenticate user
	 * 
	 * @return
	 */
	private boolean validateUser() {
		MessageContext msgContext = wsContext.getMessageContext();
		// get detail from request header
		Map httpHeaders = (Map) msgContext.get(MessageContext.HTTP_REQUEST_HEADERS);
		List userList = (List) httpHeaders.get("Username");
		List passList = (List) httpHeaders.get("Password");

		String username = "";
		String password = "";
		if (userList != null) {
			username = userList.get(0).toString();
		}
		if (passList != null) {
			password = passList.get(0).toString();
		}
		// should validate username and password from database
		if (username.equals("mngo") && password.equals("password")) {
			return true;
		}
		return false;
	}

	@Override
	public String getHellWorldAsString() {
		if (validateUser()) {
			return "Hello world";
		}
		return "Invalid username and password";
	}

	@Override
	public String echo(String input) {
		if (validateUser()) {
			return input;
		}
		return "Invalid username and password";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image retrieveImage(String name) {
		try {
			File image = new File(PATH + name);
			System.out.println(image.getAbsolutePath());
			return ImageIO.read(image);
		} catch (IOException e) {
			throw new WebServiceException("download failed", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String uploadImage(Image file) {
		if (file != null) {
			try {
				SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMMddHHmmss");
				String name = dateFormate.format(new Date());
				File image = new File(PATH + name);
				ImageIO.write((BufferedImage) file, "jpg", image);
			} catch (IOException e) {
				throw new WebServiceException("Upload failed", e);
			}
		}
		throw new WebServiceException("No data found");
	}
}
