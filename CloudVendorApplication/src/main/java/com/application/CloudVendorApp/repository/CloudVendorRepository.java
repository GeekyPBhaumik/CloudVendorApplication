package com.application.CloudVendorApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.CloudVendorApp.model.CloudVendor;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String>{
    
}
