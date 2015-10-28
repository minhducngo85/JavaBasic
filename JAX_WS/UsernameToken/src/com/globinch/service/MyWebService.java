package com.globinch.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

/**
 * These methods allow the clients to upload images, download images and a
 * simple greetCustomer method. - See more at:
 * 
 * 
 * @author mngo
 * 
 */
@WebService()
public class MyWebService implements MyWebServiceIntf {

	private final static String PATH = "D:\\mtomtest\\upload\\";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String greetCustomer(String parameter) {
		return "Hello..." + parameter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image retrieveImage(String name) {

		try {
			File image = new File(PATH + name);
			return ImageIO.read(image);
		} catch (IOException e) {
			throw new WebServiceException("download failed", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String uploadImage(Image file, String name) {
		if (file != null) {
			try {
				File image = new File(PATH + name);
				ImageIO.write((BufferedImage) file, "jpg", image);
			} catch (IOException e) {
				throw new WebServiceException("Upload failed", e);
			}
		}
		throw new WebServiceException("No data found");
	}

}
