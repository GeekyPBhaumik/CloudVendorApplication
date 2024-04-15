package com.application.CloudVendorApp.model;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Entity
@Table(name="cloud_vendor_info")
@OpenAPIDefinition(info = @Info(title = "Cloud Vendor API", version = "1.0"))
@Tag(name = "User", description = "This model holds the Cloud vendor App  Details")
public class CloudVendor {
	 @Id
	 @Schema(description = "Unique ID" , example="C2")
     private String vendorId;
     private String vendorName;
     private String vendorAddress;
     private String vendorPhoneNumber;
         
	 public CloudVendor() {
		super();
	 }
	 public CloudVendor(String vendorId, String vendorName, String vendorAddress, String 					  						vendorPhoneNumber) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorAddress = vendorAddress;
		this.vendorPhoneNumber = vendorPhoneNumber;
	 }
	 
	 public String getVendorId() {
		return vendorId;
	 }
	 
	 public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	 }
	 
	 public String getVendorName() {
		return vendorName;
	 }
	 
	 public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	 }
	 
	 public String getVendorAddress() {
		return vendorAddress;
	 }
	 
	 public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	 }
	 
	 public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	 }
	 
	 public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	 }
	 
	 @Override
	 public String toString() {
		return "CloudVendor [vendorId=" + vendorId + ", vendorName=" + vendorName +",vendorAddress=" +vendorAddress
				+ ", vendorPhoneNumber=" + vendorPhoneNumber + "]";
	 }    
}
