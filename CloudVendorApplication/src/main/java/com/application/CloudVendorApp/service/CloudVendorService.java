package com.application.CloudVendorApp.service;

import java.util.List;

import com.application.CloudVendorApp.model.CloudVendor;

public interface CloudVendorService {
		public String createVendor(CloudVendor cloudVendor);
		public String updateVendor(CloudVendor cloudVendor);
		public String deleteVendor(String vendorId);
		public CloudVendor getCloudVendor(String vendorId);
		public List<CloudVendor> getAllCloudVendor();
}
