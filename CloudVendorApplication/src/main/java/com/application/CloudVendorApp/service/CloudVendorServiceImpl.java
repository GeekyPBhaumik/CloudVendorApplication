package com.application.CloudVendorApp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.application.CloudVendorApp.exception.CloudVendorNotFoundException;
import com.application.CloudVendorApp.model.CloudVendor;
import com.application.CloudVendorApp.repository.CloudVendorRepository;

@Service
public class CloudVendorServiceImpl implements CloudVendorService{

	private final CloudVendorRepository cloudVendorRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CloudVendorServiceImpl.class);
	
	/**
	 * In Constructor Injection, in Spring Framework no need of using @Autowired annotation
	 * @param cloudVendorRepo
	 */
	public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepo) {
		this.cloudVendorRepo = cloudVendorRepo;
	}
	
	@Override
	public String createVendor(CloudVendor cloudVendor) {
		cloudVendorRepo.save(cloudVendor);
		return "CloudVendor Resource Created Successfully";
	}

	@Override
	public String updateVendor(CloudVendor cloudVendor) {
		cloudVendorRepo.save(cloudVendor);
		return "CloudVendor Resource Updated Successfully";
	}

	@Override
	public String deleteVendor(String vendorId) {
		CloudVendor cloudVendor = getCloudVendor(vendorId);
		if(cloudVendor==null) {
			return "No Cloud Vendor Found With VendorId:"+vendorId;
		}
		cloudVendorRepo.deleteById(vendorId);
		return "CloudVendor Deleted Successfully";
	}

	@Override
	public CloudVendor getCloudVendor(String vendorId) {
		Optional<CloudVendor> cloudVendorDetail = cloudVendorRepo.findById(vendorId);
		if((cloudVendorDetail.isEmpty()) && LOGGER.isErrorEnabled()) {
			LOGGER.info(String.format("No Cloud Vendor With VendorId=%s", vendorId));
			throw new CloudVendorNotFoundException("Cloud Vendor Not Found For VendorId:"+vendorId);
		}
		return (cloudVendorDetail.isPresent())?cloudVendorDetail.get():null;                
	}

	@Override
	public List<CloudVendor> getAllCloudVendor() {
		List<CloudVendor> cloudVendorList = cloudVendorRepo.findAll();
		if(cloudVendorList.isEmpty() && LOGGER.isErrorEnabled()) {
			LOGGER.info("No Cloud Vendor Currently Available");
		}
		return cloudVendorList;
	}

}