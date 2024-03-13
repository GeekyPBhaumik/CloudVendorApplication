package com.cloud.myproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@GetMapping("{vendorId}")
	public CloudVendor getCloudVendorDetails(@PathVariable String vendorId) {
		LOGGER.info("Fetching the Cloud Vendor Details");
		if(cloudVendor==null) {
			LOGGER.info("No Details Found");
		}
		return cloudVendor;
	}
	
	@GetMapping("/getVendorDetails")
	public CloudVendor getCloudVendorDetailsId(@RequestParam String vendorId) {
		LOGGER.info("Fetching the Cloud Vendor Details");
		if(cloudVendor==null) {
			LOGGER.info("No Details Found");
		}
		return cloudVendor;		
	}
	
	@PostMapping("/createNewCloudVendor")
	public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
		LOGGER.info("Creating the Cloud Vendor Details");
		this.cloudVendor = cloudVendor;
		return "CloudVendor resource created successfully";
	}
	
	@PutMapping("/updateCloudVendor")
	public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
		this.cloudVendor = cloudVendor;
		return "CloudVendor resource updated successfully";
	}
	
	@DeleteMapping("{vendorId}")
	public String deleteCloudVendorDetails(@PathVariable String vendorId) {
		this.cloudVendor = null;
		return "CloudVendor resource deleted successfully";
	}
}
