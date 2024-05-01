package com.application.CloudVendorApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.application.CloudVendorApp.model.CloudVendor;
import com.application.CloudVendorApp.repository.CloudVendorRepository;

@DataJpaTest
class CloudVendorServiceImplTest {
	/**
	 * Avoiding real-time database interaction while testing service layer
	 * Creating a mock of the CloudVendorRepository 
	 * By using @Mock, you instruct Mockito (a popular mocking framework) to create a mock implementation of the CloudVendorRepository
	 */
	@Mock	
	private CloudVendorRepository cloudVendorRepo;	
	private CloudVendorService cloudVendorService;
	AutoCloseable autoCloseable;
	CloudVendor cloudVendor;

	/**
	 * Executes before each test methods
	 */
	@BeforeEach
	void setUp() {
		//Used to automatically initialize annotated fields in the test class
		autoCloseable = MockitoAnnotations.openMocks(this);
		cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepo);
		cloudVendor = new CloudVendor("1","Amazon","USA","79001");
		cloudVendorRepo.save(cloudVendor);
	}
	/**
	 * Executes after each test methods
	 * Used for cleanup tasks for testing
	 */
	@AfterEach
	void tearDown() {
		try {
			//Closes this resource, relinquishing any underlying resources
			autoCloseable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cloudVendor = null;
		cloudVendorRepo.deleteAll();
	}
	/**
	 * Service Layer Test Cases
	 */
	/**
	 * In Mockito, the when method is used to set up the behavior of mock objects. 
	 * When you mock an object, by default,
	 * all of its methods return default values (null for objects, 0 for integers, etc.). 
	 */
	@Test
	@DisplayName("Testing For Cloud Vendor Creation")
	void testCreateVendor() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);
		when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
		assertThat(cloudVendorService.createVendor(cloudVendor)).isEqualTo("CloudVendor Resource Created Successfully");
	}
	@Test
	@DisplayName("Testing For Update Cloud Vendor Functionaliy")
	void testUpdateVendor() {
		when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
		assertThat(cloudVendorService.updateVendor(cloudVendor)).isEqualTo("CloudVendor Resource Updated Successfully");
	}
	@Test
	@DisplayName("Testing For Delete Cloud Vendor Functionaliy")
    void testDeleteVendor_Success() {
		mock(CloudVendor.class);
		mock(CloudVendorRepository.class);
        // Given
		when(cloudVendorRepo.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));        
        // When
        String result = cloudVendorService.deleteVendor(cloudVendor.getVendorId());
        // Then
        // It verifies that the deleteById is called once
        verify(cloudVendorRepo, times(1)).deleteById(cloudVendor.getVendorId());
        assertEquals("CloudVendor Deleted Successfully", result);
    }

	@Test
	@DisplayName("Testing For Get Cloud Vendor")
	void testGetCloudVendor() {
		when(cloudVendorRepo.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
		assertThat(cloudVendorService.getCloudVendor("1").getVendorName())
				  .isEqualTo(cloudVendor.getVendorName());
	}
	/**
	 * We are fetching cloud vendors 
	 * We are using singletonList because
	 * When you use Collections.singletonList(cloudVendor), 
	 * it explicitly creates a List containing only one element, which is of type CloudVendor
	 */
	@Test
	@DisplayName("Testing GetAllCloudVendor Functionality")
	void testGetAllCloudVendor() {
		when(cloudVendorRepo.findAll()).thenReturn(
			new ArrayList<CloudVendor>(Collections.singletonList(cloudVendor))
		);
		assertThat(cloudVendorService.getAllCloudVendor().get(0).getVendorPhoneNumber())
				  .isEqualTo(cloudVendor.getVendorPhoneNumber());
	}
}
