package com.application.CloudVendorApp.monitoring;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Controller
public class OtherCloudAppServiceHealthCheck implements HealthIndicator{

	private static final Logger LOGGER = LoggerFactory.getLogger(OtherCloudAppServiceHealthCheck.class); 
		
	private final Environment env;
	
	/**
	 * Constructor Injection
	 * @param env
	 */
	public OtherCloudAppServiceHealthCheck(Environment env) {
		super();
		this.env = env;
	}
	@Override
	public Health health() {
		if(isServiceUp()) {
			return Health.up().withDetail("Other Cloud App ", "is now working").build();
		}else {
			return Health.down().withDetail("Other Cloud App ", "is down").build();
		}
	}
	
	private boolean isServiceUp() {
		String address = env.getProperty("othercloud.app.address");
	    String port = env.getProperty("othercloud.app.port");
	    if(LOGGER.isInfoEnabled()) {
	    	 LOGGER.info(String.format("Address=%s,Port=%s",address,port));
	    }
	   return isAddressReachable(address,Integer.parseInt(port),3000);
	    
	}
	private boolean isAddressReachable(String address, int port, int timeout) {
		Socket isSocket = new Socket();
		try {
			//Connect this socket to the server with a specified timeout value
			isSocket.connect(new InetSocketAddress(address,port),timeout);
		    return true;
		}catch(IOException exception) {
			//connection failed so returning false
			exception.printStackTrace();
			return false;
		}
		finally {
			try {
				isSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
