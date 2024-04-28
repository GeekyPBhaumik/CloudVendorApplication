package com.application.CloudVendorApp.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Custom Response Handling 
 */
public class CloudVendorResponseHandler {
	
    private CloudVendorResponseHandler() {
	// Private constructor to prevent instantiation
	  throw new UnsupportedOperationException("Utility class - cannot be instantiated");
	}	
    public static ResponseEntity<Object> responseHandler(String message,Object object,HttpStatus httpStatus){
		Map<String,Object> responseMap = new HashMap<>();
		responseMap.put("message", message);
		responseMap.put("data", object);
		responseMap.put("httpStatus", httpStatus);		
		return new ResponseEntity<>(responseMap,httpStatus);
	}
}
