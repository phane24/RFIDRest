package com.cyient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_IMAGES")
public class ImageWarpper implements Serializable {
     
    private static final long serialVersionUID = 1L;
 
    @Id
    @JoinColumn(name = "Ticket_No", unique = true, nullable = false)
    private String ticketid;
     
    public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	@Column(name = "IMAGE_NAME", unique = false, nullable = false, length = 100)
    private String imageName;
    																	
    @Column(name = "DATA", unique = false, nullable = false, length = 16777215)
    private byte[] data;


	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
     
    //Getters and Setters
}