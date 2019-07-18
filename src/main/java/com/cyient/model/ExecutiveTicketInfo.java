package com.cyient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ExecutiveTicketInfo")
public class ExecutiveTicketInfo implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@ManyToOne
    @JoinColumn(name = "Customer_Id")
	private Customer customerId;

	@Column(name="Executive_Id")
	private String executiveId;
	
	@Column(name="Executive_Name")
	private String executiveName;
	
	@Column(name="Ticket_No")
	private String ticketNum;	
	
	@Column(name="Ticket_Description")
	private String ticketDescription;
	
	@Column(name="Status")
	private String status;	
	
	@Column(name="Severity")
	private String severity;
	
	@Column(name="Ticket_Type")
	private String ticketType;	
	
	@Column(name="Unique_Id")
	private String uniqueId;

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getExecutiveId() {
		return executiveId;
	}

	public void setExecutiveId(String executiveId) {
		this.executiveId = executiveId;
	}

	public String getExecutiveName() {
		return executiveName;
	}

	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}	
	
}