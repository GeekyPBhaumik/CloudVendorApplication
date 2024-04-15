package com.application.CloudVendorApp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.application.CloudVendorApp.model.CloudVendor;
import com.application.CloudVendorApp.response.CloudVendorResponseHandler;
import com.application.CloudVendorApp.service.CloudVendorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CloudVendorAPIController.class);
	
	private final CloudVendorService cloudVendorService;
		
	public CloudVendorAPIController(CloudVendorService cloudVendorService) {
		this.cloudVendorService = cloudVendorService;
	}	
	/**
	 * Fetching a cloud vendor using vendor Id
	 */
	@GetMapping("{vendorId}")
	@Operation(summary="Get Cloud Vendor Details By VendorID",
			   description = "This operation retrieves detailed information about an cloud vendor by its ID.\n"
                    + "Provide the vendor ID in the path parameter."
                    + "Example: /cloudvendor/{vendorId}")
	public ResponseEntity<Object> getCloudVendorDetails(@PathVariable String vendorId) {	
	    CloudVendor cloudVendor = cloudVendorService.getCloudVendor(vendorId);
	    if (cloudVendor == null) {
	        //Return a 404 Not Found status if the resource is not found	        
	    	//Custom Response Handling
	        return CloudVendorResponseHandler.responseHandler("No Cloud Vendor Found For VendorId:"+vendorId, cloudVendor, HttpStatus.NOT_FOUND);
	    }
	    // Return the cloud vendor with a 200 OK status
	    return CloudVendorResponseHandler.responseHandler("Requested Cloud Vendor Details Found For VendorId:"+vendorId, cloudVendor, HttpStatus.OK);
	}	
	/**
	 * Fetching All the cloud vendors 
	 */
	@GetMapping("/getVendorDetails")
	@Operation(summary="Get All Cloud Vendor Details")
	public ResponseEntity<Object> getAllCloudVendorDetails() {
		if(LOGGER.isErrorEnabled()) {
			LOGGER.info("No Cloud Vendor Details");
		}	
		List<CloudVendor> cloudVendor = cloudVendorService.getAllCloudVendor();
	    if (cloudVendor.isEmpty()) {	        
	        return CloudVendorResponseHandler.responseHandler("No Cloud Vendor Details Found", cloudVendor, HttpStatus.NOT_FOUND);
	    }
	    return CloudVendorResponseHandler.responseHandler("Requested All Cloud Vendor Details Found", cloudVendor, HttpStatus.OK);
	}	
	/**
	 * Creating a Cloud Vendor
	 */
	@PostMapping("/createNewCloudVendor")
	@Operation(summary="Create New Cloud Vendor")
	public ResponseEntity<String> createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
	    LOGGER.info("Creating the Cloud Vendor Details");
	    String message = cloudVendorService.createVendor(cloudVendor);
	    return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}	
	/**
	 * Updating the Cloud Vendor Details
	 */
	@PutMapping("/updateCloudVendor")
	@Operation(summary="Update All Cloud Vendor Details")
	public ResponseEntity<String> updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
		LOGGER.info("Updating the Cloud Vendor Details");
		String message = cloudVendorService.updateVendor(cloudVendor);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}	
	/**
	 * Deleting the Cloud Vendor 
	 */
	@DeleteMapping("{vendorId}")
	@Operation(summary="Delete Cloud Vendor By VendorId")
	public ResponseEntity<String> deleteCloudVendorDetails(@PathVariable String vendorId) {
		if(LOGGER.isErrorEnabled()) {
		   LOGGER.info(String.format("Deleting the Cloud Vendor with vendorId=%s", vendorId));
		}		
		String message = cloudVendorService.deleteVendor(vendorId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
