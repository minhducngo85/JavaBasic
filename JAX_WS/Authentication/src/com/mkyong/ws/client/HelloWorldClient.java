package com.mkyong.ws.client;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPBinding;

import com.mkyong.ws.IHelloWorld;

/**
 * the web service client
 * 
 * upload file somehow still not working
 * 
 * @author mngo
 * 
 */
public class HelloWorldClient {
	/** for web service publisher */
	// private static final String WS_URL =
	// "http://localhost:9999/ws/hello?wsdl";

	/** for Tomcat */
	private static final String WS_URL = "http://localhost:8082/Authentication/hello?wsdl";
	
	private final static String PATH = "E:\\mtomtest\\client\\";

	private Service service = null;

	IHelloWorld helloService = null;

	static {
		// for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			}
		});
	}

	public HelloWorldClient() throws MalformedURLException {
		URL url = new URL(WS_URL);
		QName qname = new QName("http://ws.mkyong.com/", "HelloWorldImplService");
		service = Service.create(url, qname);

		helloService = service.getPort(IHelloWorld.class);

		// set username and password
		Map<String, Object> reqContext = ((BindingProvider) helloService).getRequestContext();
		reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Username", Collections.singletonList("mngo"));
		headers.put("Password", Collections.singletonList("password"));
		reqContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
	}

	/**
	 * echo servie
	 * 
	 * @param input
	 * @return
	 * @throws MalformedURLException
	 */
	public String echo(String input) throws MalformedURLException {
		return helloService.echo(input);
	}

	public String helloWorld() {
		return helloService.getHellWorldAsString();
	}

	/**
	 * get content of image file
	 * 
	 * @param imgName
	 * @return
	 */
	public Image downloadImage(String imgName) {
		Image imageContent = helloService.retrieveImage(imgName);
		return imageContent;
	}

	public void upload(String fileName) throws IOException {
		BindingProvider bp = (BindingProvider) helloService;
		SOAPBinding binding = (SOAPBinding) bp.getBinding();
		binding.setMTOMEnabled(true);

		File file = new File(PATH + fileName);
		Image imgUpload = ImageIO.read(file);
		String status = helloService.uploadImage(imgUpload);
		System.out.println(status);
	}

	/**
	 * the main class
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		HelloWorldClient client = new HelloWorldClient();
		System.out.println(client.echo("I'm a echo method"));
		System.out.println(client.helloWorld());

		/************ test download ***************/
		Image image = client.downloadImage("rss.jpg");

		// display it in frame
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		frame.setVisible(true);

		// save image to disk
		BufferedImage bi = (BufferedImage) image;
		File f = new File(PATH + "download.jpg");
		ImageIO.write(bi, "jpg", f);

		/** tets upload - not working */
//		String filename = "rss.png";
//		client.upload(filename);

	}
}
