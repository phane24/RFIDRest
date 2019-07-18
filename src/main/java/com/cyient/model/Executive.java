package com.cyient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Executive")
public class Executive implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@Column(name="Executive_Id")
	private String executiveId;
	
	@ManyToOne
	@JoinColumn(name="Customer_Id")
	private Customer customerId;	
	
	@Column(name="Email_Id")
	private String emailId;
	
	@Column(name="Executive_Name")
	private String executiveName;
	
	@Column(name="Mobile")
	private String mobile;	
	
	@Column(name="Region")
	private String region;

	public String getExecutiveId() {
		return executiveId;
	}

	public void setExecutiveId(String executiveId) {
		this.executiveId = executiveId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getExecutiveName() {
		return executiveName;
	}

	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}			
}