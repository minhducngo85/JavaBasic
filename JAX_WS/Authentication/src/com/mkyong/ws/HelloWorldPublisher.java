package com.mkyong.ws;

import javax.xml.ws.Endpoint;

/**
 * in order to run WS as an stand-alone app
 * 
 * @author mngo
 * 
 */
public class HelloWorldPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
	}
}
