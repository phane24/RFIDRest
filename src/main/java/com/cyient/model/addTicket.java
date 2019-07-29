package com.cyient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Ticket")
public class addTicket implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@Column(name="Ticket_Id")
	private String ticketId;

	@Column(name="Ticket_Type")
	private String ticketType;	
	
	@Column(name="Region")
	private String region;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Exchange")
	private long exchange;	
	
	@Column(name="Floor")
	private String floor;	
	
	@Column(name="Suite")
	private String suite;
	
	@Column(name="Rack")
	private String rack;	
	
	@Column(name="Sub_rack")
	private String sub_rack;
	
	@Column(name="Customer_Id")
	private String customerId;	
	
	@Column(name="Unique_Id")
	private String uniqueId;
	
	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getExchange() {
		return exchange;
	}

	public void setExchange(long exchange) {
		this.exchange = exchange;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getSub_rack() {
		return sub_rack;
	}

	public void setSub_rack(String sub_rack) {
		this.sub_rack = sub_rack;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	

}