package com.cyient.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Ticketing")
public class Ticketing implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@Column(name="Ticket_No")
	private String ticketNum;

	@Column(name="Ticket_Type")
	private String ticketType;	
	
	

	@Column(name="Customer_Name")
	private String customerName;
	
	@Column(name="Exchange_Name")
	private String exchangeName;
	
	@Column(name="Ticket_Description")
	private String ticketDescription;
	
	@Column(name="Open_Date")
	@Temporal(TemporalType.DATE)
	private Date openDate;
	
	@Column(name="Open_Time")
	@Temporal(TemporalType.TIME)
	private Date openTime;
	
	@Column(name="Closed_Date")
	@Temporal(TemporalType.DATE)
	private Date closedDate;
	
	@Column(name="Closed_Time")
	@Temporal(TemporalType.TIME)
	private Date closedTime;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Severity")
	private String severity;

	@Column(name="Unique_Id")
	private String uniqueId;
	
	@OneToOne
    @JoinColumn(name = "Customer_Id")
	private Customer customer;
	
	
	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
	
	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
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
	
	 public Customer getCustomer() {
		  return customer;
		 }
		 public void setCustomer(Customer customer) {
		  this.customer = customer;
		 }

}