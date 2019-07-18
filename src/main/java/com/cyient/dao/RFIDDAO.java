package com.cyient.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cyient.model.Customer;
import com.cyient.model.Design;
import com.cyient.model.Exchange;
import com.cyient.model.Executive;
import com.cyient.model.ExecutiveTicketInfo;
import com.cyient.model.Inventory;
import com.cyient.model.Ticketing;
import com.cyient.model.User;

public interface RFIDDAO {
	
	@Transactional
	public void addUser(User user);
	
	@Transactional
	public User getAllUsersOnCriteria(String username,String password);
	
	@Transactional
	public void addExchange(Exchange exchange);
	
	@Transactional
	public void addExecutive(Executive executive);
	
	@Transactional
	 public List getAllData();
	
	@Transactional
	 public List<Ticketing> getOpenTicketsData();
	
	@Transactional
	 public List<Ticketing> openTicketsCountData();
	
	@Transactional
	 public List<Ticketing> getClosedTicketsData();
	
	@Transactional
	 public List<Ticketing> closedTicketsCountData();
	
	@Transactional
	 public List<Ticketing> getHistoryTicketsData();
	
	@Transactional
	 public List<Ticketing> historyTicketsCountData();
	
	@Transactional
	 public List<Executive> getAllExecutivesData();
	
	@Transactional
	 public List<Ticketing> getAllTicketsData();
	
	@Transactional
	 public Executive getExecutivesData(String executiveId);
	
	@Transactional
	 public List<Ticketing> getTicketsData(String ticketNum);
	
	@Transactional
	public void assignExecutive(ExecutiveTicketInfo executiveTicket);
	
	@Transactional
	 public List<Ticketing> getExecOpenTicketsData(String username);
	
	@Transactional
	 public List<Ticketing> getExecClosedTicketsData(String username);
	
	@Transactional
	public String save_customer(Customer customer);
	
	@Transactional
	public Boolean Authentication(String companyid,String secretkey);

	@Transactional
	public List<ExecutiveTicketInfo>getTickets_based_on_Executive(String Executive_id);
	
	@Transactional	
	public List<Inventory> getInventory(String customer_id);

	@Transactional	
	public List<Design> getDesign(String customer_id);

	@Transactional	
	public List<Customer> getCustomer(String customer_id);
	
	@Transactional	
	public List<User> getAuthenticate_FE(String username,String password);
	
	
	@Transactional
	public Boolean update_customer(Customer customer);

	@Transactional
	public Boolean update_design(Design design);
	
	@Transactional
	public Boolean update_inventory(Inventory inventory);

}
