package com.application.CloudVendorApp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.application.CloudVendorApp.model.CloudVendor;
import com.application.CloudVendorApp.service.CloudVendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(CloudVendorAPIController.class)
class CloudVendorAPControllerTest {
	
	/**
	 * Controller Layer Testing
	 */	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CloudVendorService cloudVendorService;
	CloudVendor cloudVendorOne;
	CloudVendor cloudVendorTwo;
	List<CloudVendor> cloudVendorList = new ArrayList<>();
	
	/**
	 * Executes before each test methods
	 */
	@BeforeEach
	void setUp() {
		cloudVendorOne = new CloudVendor("1","Amazon",
						 "USA","9000129221");
		cloudVendorTwo = new CloudVendor("2","GCP",
				 		 "UK","9008129290");
		cloudVendorList.add(cloudVendorOne);
		cloudVendorList.add(cloudVendorTwo);
	}
	/**
	 * Executes after each test methods
	 * Used for cleanup tasks for testing
	 */
	@AfterEach
	void tearDown() {
		
	}
	@Test
	@DisplayName("Testing Get Cloud Vendor By Vendor Id")
	void testGetCloudVendorDetails() throws Exception {
		when(cloudVendorService.getCloudVendor("1"))
			       .thenReturn(cloudVendorOne);
		this.mockMvc.perform(get("/cloudvendor/1"))
			.andDo(print()).andExpect(status().isOk());		
	}
	@Test
	@DisplayName("Testing Get All Vendor Details")
	void testGetAllVendorDetails() throws Exception{
		when(cloudVendorService.getAllCloudVendor())
					.thenReturn(cloudVendorList);
		this.mockMvc.perform(get("/cloudvendor/getVendorDetails"))
			.andDo(print()).andExpect(status().isOk());
	}
	@Test
	@DisplayName("Testing Delete Cloud Vendor Details")
	void testDeleteCloudVendorDetais() throws Exception {
		when(cloudVendorService.deleteVendor("1"))
							.thenReturn("CloudVendor Deleted Successfully");
		this.mockMvc.perform(delete("/cloudvendor/1"))
					.andDo(print()).andExpect(status().isOk());
	}
	@Test
	@DisplayName("Testing Create Cloud Vendor Details")
	void testCreateCloudVendorDetails() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		String requestJson =  mapper.writeValueAsString(cloudVendorOne);
		
		when(cloudVendorService.createVendor(cloudVendorOne))
							   .thenReturn("CloudVendor Resource Created Successfully");
	    this.mockMvc.perform(post("/cloudvendor/createNewCloudVendor")
	    		    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
	    		    .content(requestJson))
	    			.andDo(print()).andExpect(status().isCreated());
	}	
}
