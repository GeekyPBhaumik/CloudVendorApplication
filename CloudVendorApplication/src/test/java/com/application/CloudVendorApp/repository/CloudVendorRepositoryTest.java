package com.application.CloudVendorApp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.application.CloudVendorApp.model.CloudVendor;

/**
 * DataJpaTest annotation tells the SpringBoot that it is a test class
 * and also to use in-memory database for testing any method
 * involving database operations
 */
@DataJpaTest
class CloudVendorRepositoryTest {
	
	@Autowired
	private CloudVendorRepository cloudVendorRepo;

	CloudVendor cloudVendor;
	/**
	 * Executes before each test methods
	 */
	@BeforeEach
	void setUp() {
		cloudVendor = new CloudVendor("1","Amazon","USA","79001");
		cloudVendorRepo.save(cloudVendor);
	}
	/**
	 * Executes after each test methods
	 * Used for cleanup tasks for testing
	 */
	@AfterEach
	void tearDown() {
		cloudVendor = null;
		cloudVendorRepo.deleteAll();
	}
	/**
	 * Repository Layer Test Case
	 * importing the assertj package for assertion 
	 * not the junit jupiter package
	 */
	@Test
	@DisplayName("Testing the repo method for finding vendors by name")
	void testFindByVendorName() {
		List<CloudVendor> cloudVendorList = cloudVendorRepo.findByVendorName("Amazon");
	    assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
	    assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
	}	
	/**
	 * Repository Layer Test Case
	 */
	@Test
	@DisplayName("Testing the repo method for not finding any vendor")
	void testCloudVendorName_NotFound() {
		List<CloudVendor> cloudVendorList = cloudVendorRepo.findByVendorName("GCP");
		assertThat(cloudVendorList).isEmpty();
	}	
}
