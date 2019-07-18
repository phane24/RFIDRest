package com.cyient.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;

import com.cyient.dao.RFIDDAO;
import com.cyient.exceptions.CustomException;
import com.cyient.model.Customer;
import com.cyient.model.Design;
import com.cyient.model.ExecutiveTicketInfo;
import com.cyient.model.Inventory;
import com.cyient.model.Ticketing;
import com.cyient.model.User;
import com.google.gson.Gson;
import com.mysql.jdbc.Blob;

@RestController
@RequestMapping("/Ticketing")
public class RIFDRestController {
	@Autowired
	private RFIDDAO rfidDAO;
	// add code for the "/hello" endpoint
	private static final Logger logger = Logger
			.getLogger(RIFDRestController.class);

   
    
	JSONParser parser = new JSONParser();
	Gson gson = new Gson();

	
	//String Error = "{ \"status\" : \"Authentication Error \"}";
	
	JSONObject Success_string =  new JSONObject();

	JSONObject Error = new JSONObject();
	//JSONObject Success_string = new JSONObject();
	
	 public RIFDRestController()
	    {
			Error.put("status", "Authentication Error");
			Success_string.put("status", "Customer Added");
	    }

	
	@GetMapping(path="/getTickets",produces = "application/json")
	public String  getTickets(@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid) throws ParseException 
	{

		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{

			List<Ticketing> Ticket_list = rfidDAO.getOpenTicketsData();

			//JSONArray json = (JSONArray) parser.parse();

			return gson.toJson(Ticket_list);
		}
		else

		{
			return Error.toString();
		}
	}



	@GetMapping(path="/getTickets_exec/{id}",produces = "application/json")
	public String getTickets_based_on_Executive(@PathVariable String id)
	{
		return gson.toJson(rfidDAO.getTickets_based_on_Executive(id));
	}

	@GetMapping(path="/getTickets_Executive",produces = "application/json")
	public String getTickets_based_on_Executive_header(@RequestHeader("Executive-Id") String Executive_Id,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid)
	{
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{					
		return gson.toJson(rfidDAO.getTickets_based_on_Executive(Executive_Id));
		}
		else
		{
			return Error.toString();
		}
	}
	
	@GetMapping(path="/getInventory",produces = "application/json")
	public String getInventory(@RequestHeader("customer-id") String customerid,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid)
	{
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
		return gson.toJson(rfidDAO.getInventory(customerid));
		}
		else
		{
			return Error.toString();
		}
	}
	
	
	@GetMapping(path="/getDesign",produces = "application/json")
	public String getDesign(@RequestHeader("customer-id") String customerid,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid)
	{
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
		return gson.toJson(rfidDAO.getDesign(customerid));
		}
		else
		{
			return Error.toString();
		}
	}
	
	@GetMapping(path="/getCustomer",produces = "application/json")
	public String getCustomer(@RequestHeader("customer-id") String customerid,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid)
	{
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
		return gson.toJson(rfidDAO.getCustomer(customerid));
		}
		else
		{
			return Error.toString();
		}
	}
	
	@GetMapping(path="/Authenticate_FE",produces = "application/json")
	public String getAuthenticate_FE(@RequestHeader("username") String username,@RequestHeader("password") String password,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid)
	{
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			List<User> Authenticate_Data = rfidDAO.getAuthenticate_FE(username,password);
			JSONObject Success = new JSONObject();
			JSONObject failure = new JSONObject();
			
			Success.put("status","Valid Credentials");
			failure.put("status","Invalid Credentials");

			if(Authenticate_Data.size()==0)
			{
				return failure.toString();
			}
			else
			{
				return Success.toString();
			}
		}
		else
		{
			JSONObject obj = new JSONObject();
			obj.put("status", "Authentication Error");
			return obj.toString();
		}
	}
	
	@GetMapping(path="/Authenticate",produces = "application/json")
	public String getAuthenticate(@RequestHeader("username") String username,@RequestHeader("password") String password,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid) throws ParseException
	{
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			List<User> Authenticate_Data = rfidDAO.getAuthenticate_FE(username,password);
			JSONObject Success = new JSONObject();
			JSONObject failure = new JSONObject();
			
			Success.put("status","Valid Credentials");
			failure.put("status","Invalid Credentials");

			if(Authenticate_Data.size()==0)
			{
				return failure.toString();
			}
			else
			{
				
				/*List<ExecutiveTicketInfo> list_demo =  rfidDAO.getTickets_based_on_Executive(username);
				System.out.println(username+"Kiran");

				System.out.println(list_demo.get(0).getTicketType());
				JSONParser parser = new JSONParser(); 
				JSONArray json = (JSONArray) parser.parse(gson.toJson(rfidDAO.getTickets_based_on_Executive(username)));
				System.out.println(json.get(0));
				JSONObject jobj = new JSONObject(list_demo.get(0).getCustomerId());
				jobj.put("ticketType", list_demo.get(0).getTicketType());
				System.out.println(jobj+" customer object");
				JSONObject objofobj = new JSONObject(json.get(0));
				objofobj.remove("customerId");
				System.out.println(objofobj);
				objofobj.append("customerId", jobj);
				System.out.println("after"+objofobj);*/

				
				
				
				return gson.toJson(rfidDAO.getTickets_based_on_Executive(username));
				//return Success.toString();
			}
		}
		else
		{
			JSONObject obj = new JSONObject();
			obj.put("status", "Authentication Error");
			return obj.toString();
		}
	}
	
	
	
	
	@PostMapping(path = "/add_customer", consumes = "application/json", produces = "application/json")
	public String addMember(@RequestBody Customer customer,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid) throws ParseException
	{
		//code
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			String s= rfidDAO.save_customer(customer);	

			return Success_string.toString();
		}
		else
		{
			return Error.toString();
		}
	}

	
	@PostMapping(path = "/update_customer", consumes = "application/json", produces = "application/json")
	public String update_customer(@RequestBody Customer customer,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid) throws ParseException
	{
		JSONObject status = new JSONObject();
		//code
		
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			if(rfidDAO.update_customer(customer)==true)	
			status.put("status","Customer Data updated successfully");
			return status.toString();
		}
		else
		{
			return Error.toString();
		}
	}
	
	
	
	@PostMapping(path = "/update_design", consumes = "application/json", produces = "application/json")
	public String update_design(@RequestBody Design design,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid,@RequestHeader("customer-id") String customerid) throws ParseException
	{
		JSONObject status = new JSONObject();
		//code
		
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			if(rfidDAO.update_design(design,customerid)==true)	
			status.put("status","Design Data updated successfully");
			return status.toString();
		}
		else
		{
			return Error.toString();
		}
	}
	
	
	
	@PostMapping(path = "/update_inventory", consumes = "application/json", produces = "application/json")
	public String update_inventory(@RequestBody Inventory inventory,@RequestHeader("secret-key") String secretkey,@RequestHeader("customer-id") String customerid,@RequestHeader("company-id") String companyid) throws ParseException
	{
		JSONObject status = new JSONObject();
		//code
		
		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			if(rfidDAO.update_inventory(inventory,customerid)==true)	
			status.put("status","Inventory Data updated successfully");
			return status.toString();
		}
		else
		{
			return Error.toString();
		}
	}
	
	
	
/*	@PostMapping(path = "/update_ticket", consumes = "application/json", produces = "application/json")
	public String update_ticket(@RequestBody JSONArray data,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid) throws ParseException
	{
		JSONObject status = new JSONObject();
		//code

		if(rfidDAO.Authentication(companyid, secretkey)==true)
		{
			
		}
		else
		{
			return Error.toString();

		}
	
			//if(rfidDAO.update_inventory(inventory)==true)	
			//status.put("status","Inventory Data updated successfully");			
			return data;
		
		
	}*/
	
	@PostMapping(path = "/upload_image", consumes = { "multipart/form-data" })
	public String file(@RequestParam("image") MultipartFile file,@RequestHeader("secret-key") String secretkey,@RequestHeader("company-id") String companyid,@RequestHeader("customer-id") String customerid,@RequestHeader("ticket-id") String ticketid) throws ParseException, IOException
	{
		JSONObject status = new JSONObject();
		
	    byte[] bytes = file.getBytes();
	    
	    status.put("status", "File uploaded");
		return status.toString();

		
		
	}
	
	
	
	
	
	
	
	@GetMapping("/test_header")
	public String headertest() 
	{
		List<ExecutiveTicketInfo> temp = rfidDAO.getTickets_based_on_Executive("EXEC009");
		
		return temp.toString();
	}
	
	


}
