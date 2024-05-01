package com.application.CloudVendorApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CloudVendorGlobalExceptionHandler {
      
	@ExceptionHandler(CloudVendorNotFoundException.class)
	public ResponseEntity<String> handleCloudVendorNotFoundException(CloudVendorNotFoundException cloudVendorNotFoundException){
		return new ResponseEntity<>(cloudVendorNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
}
