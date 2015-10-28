package com.mkyong.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.mkyong.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString() {
		return "Hello world jax ws";
	}

}
