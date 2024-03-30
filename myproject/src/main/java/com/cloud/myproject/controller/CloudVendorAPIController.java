package com.cloud.myproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.myproject.model.CloudVendor;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CloudVendorAPIController.class);
	
	CloudVendor cloudVendor;
	
	/**
	 * Fetching the Cloud Vendor API Details using Vendor Id
	 */
	@GetMapping("{vendorId}")
	public ResponseEntity<CloudVendor> getCloudVendorDetails(@PathVariable String vendorId) {
	    LOGGER.info("Fetching the Cloud Vendor Details");
	    if (cloudVendor == null) {
	        LOGGER.info("No Details Found");
	        // Return a 404 Not Found status if the resource is not found
	        return ResponseEntity.notFound().build();
	    }
	    // Return the cloud vendor with a 200 OK status
	    return ResponseEntity.ok(cloudVendor);
	}
	
	/**
	 * Fetching the Cloud Vendor API Details using Vendor Id
	 */
	@GetMapping("/getVendorDetails")
	public ResponseEntity<CloudVendor> getCloudVendorDetailsById(@RequestParam String vendorId) {
	    LOGGER.info("Fetching the Cloud Vendor Details");
	    if (cloudVendor == null) {
	        LOGGER.info("No Details Found");
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(cloudVendor);
	}
	
	/**
	 * Creating a Cloud Vendor
	 */
	@PostMapping("/createNewCloudVendor")
	public ResponseEntity<String> createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
	    LOGGER.info("Creating the Cloud Vendor Details");
	    this.cloudVendor = cloudVendor;
	    String message = "CloudVendor resource created successfully";
	    return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
	
	/**
	 * Updating the Cloud Vendor Details
	 */
	@PutMapping("/updateCloudVendor")
	public ResponseEntity<String> updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
		LOGGER.info("Updating the Cloud Vendor Details");
		this.cloudVendor = cloudVendor;
		String message = "CloudVendor resource updated successfully";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	/**
	 * Deleting the Cloud Vendor 
	 */
	@DeleteMapping("{vendorId}")
	public String deleteCloudVendorDetails(@PathVariable String vendorId) {
		this.cloudVendor = null;
		return "CloudVendor resource deleted successfully";
	}
}
