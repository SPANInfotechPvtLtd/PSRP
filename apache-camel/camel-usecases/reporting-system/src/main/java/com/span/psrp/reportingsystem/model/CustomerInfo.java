package com.span.psrp.reportingsystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity(name="CUSTOMER_INFO")
@NamedQuery(name = "customerRead", query = "select cus from CUSTOMER_INFO cus where cus.customerNo = :customerno")
public class CustomerInfo implements Serializable{

	private static final long serialVersionUID = 1788731994385494018L;
	@Id
    @Column(name="CUST_ID")
	private  Integer customerId;
	@Column(name="CUST_NO")
	private  String customerNo;
	@Column(name="CUST_LAST_NAME")
	private  String lastName;
	@Column(name="CUST_FIRST_NAME")
	private  String firstName;
	@Column(name="CUST_PHONE")
	private  String phone;
	@Column(name="CUST_ADDR_LINE1")
	private  String addressLine1;
	@Column(name="CUST_ADDR_LINE2")
	private  String addressLine2;   
	@Column(name="CUST_CITY")
	private  String city;
	@Column(name="CUST_STATE")
	private  String state;
	@Column(name="CUST_POSTAL_CODE")
	private  String postalCode;
	@Column(name="CUST_COUNTRY")
	private  String country;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
