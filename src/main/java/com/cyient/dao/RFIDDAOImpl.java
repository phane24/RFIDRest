package com.cyient.dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cyient.model.Customer;
import com.cyient.model.Design;
import com.cyient.model.Executive;
import com.cyient.model.ExecutiveTicketInfo;
import com.cyient.model.Headers_keys;
import com.cyient.model.ImageWarpper;
import com.cyient.model.Inventory;
import com.cyient.model.Taginformation;
import com.cyient.model.Taginformation_history;
import com.cyient.model.Ticketing;
import com.cyient.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Repository
public class RFIDDAOImpl implements RFIDDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JavaMailSender mailSender;
	
	Gson gson = new Gson();


	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	public User getAllUsersOnCriteria(String username,String password) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("username",username));
		c.add(Restrictions.eq("password",password));
		return (User)c.uniqueResult();
	} 

	public User getUsername(String username) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("username",username));
		return (User)c.list().get(0);
	} 



	public void addExecutive(Executive executive) {
		sessionFactory.getCurrentSession().saveOrUpdate(executive);
	}

	@SuppressWarnings("rawtypes")

	public List getAllData() {
		return null;
		//sessionFactory.getCurrentSession().createQuery("select * from region").list();
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getOpenTicketsData() {		
		//return sessionFactory.getCurrentSession().createQuery("from Ticketing where Status='OPEN'").list();

		Criteria c = sessionFactory.getCurrentSession().createCriteria(Ticketing.class);

		return (List<Ticketing>)c.list();
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> openTicketsCountData() {		
		return sessionFactory.getCurrentSession().createQuery("select count(*) from Ticketing where Status='OPEN'").list();
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> closedTicketsCountData() {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		String date1 = dateFormat.format(cal.getTime());
		String date2 = dateFormat.format(cal1.getTime());
		return sessionFactory.getCurrentSession().createQuery("select count(*) from Ticketing where Status='CLOSED' and Closed_Date<='"+date2+"' and Closed_Date>='"+date1+"'").list();	
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> historyTicketsCountData() {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -7);	
		String date1 = dateFormat.format(cal.getTime());		
		return sessionFactory.getCurrentSession().createQuery(" select count(*) from Ticketing where Status='CLOSED' and Closed_Date<'"+date1+"'").list();	
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getClosedTicketsData() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		String date1 = dateFormat.format(cal.getTime());
		String date2 = dateFormat.format(cal1.getTime());
		return sessionFactory.getCurrentSession().createQuery("from Ticketing where Status='CLOSED' and Closed_Date<='"+date2+"' and Closed_Date>='"+date1+"'").list();
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getHistoryTicketsData() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -7);	
		String date1 = dateFormat.format(cal.getTime());		
		return sessionFactory.getCurrentSession().createQuery("from Ticketing where Status='CLOSED' and Closed_Date<'"+date1+"'").list();
	}

	@SuppressWarnings("unchecked")

	public List<Executive> getAllExecutivesData() {
		return sessionFactory.getCurrentSession().createCriteria(Executive.class).list();
	}


	public Executive getExecutivesData(String executiveId) {
		return (Executive) sessionFactory.getCurrentSession().get(Executive.class, executiveId);
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getTicketsData(String ticketNum) {
		return sessionFactory.getCurrentSession().createQuery("from Ticketing where Ticket_No='"+ticketNum+"'").list();
	}


	public void assignExecutive(ExecutiveTicketInfo executiveTicket){
		sessionFactory.getCurrentSession().saveOrUpdate(executiveTicket);
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getExecOpenTicketsData(String username) {	
		System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where Status='OPEN' and Executive_Name='"+username+"'").list();
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getExecClosedTicketsData(String username) {
		System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where Status='CLOSED' and Executive_Name='"+username+"'").list();
	}

	@SuppressWarnings("unchecked")

	public List<Ticketing> getAllTicketsData() {
		return sessionFactory.getCurrentSession().createCriteria(Ticketing.class).list();
	}

	public String save_customer(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
		return "Success";

	}


	public Boolean Authentication(String companyid,String secretkey)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Headers_keys.class);
		c.add(Restrictions.eq("secret_key",secretkey));
		c.add(Restrictions.eq("company_id",companyid));
		List<Headers_keys>dummy = c.list();
		
		if(dummy.size()==0)
		{
			return false;
		}else
		{
			return true;
		}
	}
	
/*	public Boolean Device_Authentication(String macid,String technicanid)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Device_Assigned_to_Technician.class);
		c.add(Restrictions.eq("MAC_ID",macid));
		c.add(Restrictions.eq("Executive_Id",technicanid));
		List<Device_Assigned_to_Technician>dummy = c.list();		
		if(dummy.size()==0)
		{
			return false;
		}else
		{
			return true;
		}
	}
*/

	public List<ExecutiveTicketInfo>getTickets_based_on_Executive(String Executive_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(ExecutiveTicketInfo.class);
		c.add(Restrictions.eq("executiveId",Executive_id));
		c.add(Restrictions.eq("status","InProgress"));

		ArrayList<String> Ticket_ID = new ArrayList<String>();
		//mylist.add(mystring);
		List <ExecutiveTicketInfo> old_object = (List<ExecutiveTicketInfo>) c.list();

		if(old_object!=null)
		{
			for(ExecutiveTicketInfo data:old_object)
			{
				Ticket_ID.add(data.getTicketNum());
				//temp=data.getOrgStock();
			}
		}


		//Criteria c_Ticketing = sessionFactory.getCurrentSession().createCriteria(Ticketing.class);
		//c_Ticketing.add(Restrictions.eq("ticketNum",Ticket_ID.get(0)));

		return (List<ExecutiveTicketInfo>)c.list();


		//List<Headers_keys>dummy = criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Customer>getTickets_based_on_Executive_CustomerID(String Executive_id)
	{


		return sessionFactory.getCurrentSession().createQuery("select customer from ExecutiveTicketInfo where status='InProgress' and Executive_Id='"+Executive_id+"'").list();	



		//List<Headers_keys>dummy = criteria.list();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Inventory> getInventory(String customer_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Inventory.class);
		Customer object = new Customer();
		object.setCustomerId(customer_id);
		c.add(Restrictions.eq("customerId",object));
		//c.add(Restrictions.eq("region",region));
		//c.add(Restrictions.eq("city",city));

		return (List<Inventory>)c.list();

	}

	public List<Design> getDesign(String customer_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Design.class);
		Customer object = new Customer();
		object.setCustomerId(customer_id);
		c.add(Restrictions.eq("customerId",object));
		//c.add(Restrictions.eq("region",region));
		//c.add(Restrictions.eq("city",city));

		return (List<Design>)c.list();
	}

	public List<Customer> getCustomer(String customer_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		c.add(Restrictions.eq("customerId",customer_id));
		//c.add(Restrictions.eq("region",region));
		//c.add(Restrictions.eq("city",city));
		
		return (List<Customer>)c.list();
	}

	public List<User> getAuthenticate_FE(String username,String password)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("username",username));
		c.add(Restrictions.eq("password",password));
		c.add(Restrictions.eq("type","FeildExecutive"));


		return (List<User>)c.list();
	}


	//@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public Boolean update_customer(Customer customer) {
		try{
			Criteria c = sessionFactory.getCurrentSession().createCriteria(Customer.class);
			c.add(Restrictions.eq("customerId",customer.getCustomerId()));
			Customer object = (Customer)c.list().get(0);

			object.setAddress(customer.getAddress());
			object.setCity(customer.getCity());
			object.setCustomerId(customer.getCustomerId());
			object.setCustomerName(customer.getCustomerName());	
			object.setCustomerStatus(customer.getCustomerStatus());
			object.setFax(customer.getFax());
			object.setPartial(customer.getPartial());
			object.setPhoneNum(customer.getPhoneNum());
			object.setPostalCode(customer.getPostalCode());
			object.setSurname(customer.getSurname());
			object.setUserId(customer.getUserId());

			sessionFactory.getCurrentSession().update(object);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}

	public Boolean update_design(Design design,String customerid) {
		try{
			Criteria c = sessionFactory.getCurrentSession().createCriteria(Design.class);
			Customer object = new Customer();
			object.setCustomerId(customerid);
			c.add(Restrictions.eq("customerId",object));


			Design Desgine_updated = (Design)c.list().get(0);
			Desgine_updated.setExchangeName(design.getExchangeName());
			Desgine_updated.setShelfName(design.getShelfName());
			Desgine_updated.setStartPoint(design.getStartPoint());
			Desgine_updated.setEndPoint(design.getEndPoint());
			Desgine_updated.setLength(design.getLength());
			Desgine_updated.setAddress(design.getAddress());
			Desgine_updated.setFloor(design.getFloor());
			Desgine_updated.setSuite(design.getSuite());
			Desgine_updated.setVertIn(design.getVertIn());



			sessionFactory.getCurrentSession().update(Desgine_updated);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}

	public Boolean update_inventory(Inventory inventory,String customerid) {
		try{

			Criteria c = sessionFactory.getCurrentSession().createCriteria(Inventory.class);
			Customer object = new Customer();
			object.setCustomerId(customerid);
			c.add(Restrictions.eq("customerId",object));
			Inventory Inventory_modified = (Inventory)c.list().get(0);


			Inventory_modified.setExchangeName(inventory.getExchangeName());
			Inventory_modified.setShelfName(inventory.getShelfName());
			Inventory_modified.setLength(inventory.getLength());
			Inventory_modified.setInventoryStatusCode(inventory.getInventoryStatusCode());
			Inventory_modified.setOwnershipTypeCode(inventory.getOwnershipTypeCode());
			Inventory_modified.setSiteName(inventory.getSiteName());
			Inventory_modified.setInstalledDate(inventory.getInstalledDate());
			Inventory_modified.setOperationalState(inventory.getOperationalState());


			sessionFactory.getCurrentSession().update(Inventory_modified);

			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}

/*
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)	
	public Boolean upload_file(byte[] file,String ticketid)
	{
		try {
			byte[] bytes = file.getBytes();

			if(bytes.length==0)
			{
				return false;
			}
			ImageWarpper imageWarpper =  new ImageWarpper();
			imageWarpper.setTicketid(ticketid);
			imageWarpper.setImageName(ticketid+".jpeg");
			imageWarpper.setData(bytes);
			sessionFactory.getCurrentSession().saveOrUpdate(imageWarpper);
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;

		}

	

	}
	
	*/
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)	
	public Boolean upload_file(byte[] file,String ticketid)
	{
		try {

			if(file.length==0)
			{
				return false;
			}
			ImageWarpper imageWarpper =  new ImageWarpper();
			imageWarpper.setTicketid(ticketid);
			imageWarpper.setImageName(ticketid+".jpeg");
			imageWarpper.setData(file);
			sessionFactory.getCurrentSession().saveOrUpdate(imageWarpper);
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;

		}

	

	}
	
	
	public Boolean update_ticket(String ticketid,String status,String ExecutiveId) {
		try{

			Criteria c = sessionFactory.getCurrentSession().createCriteria(ExecutiveTicketInfo.class);
			c.add(Restrictions.eq("ticketNum",ticketid));
			c.add(Restrictions.eq("executiveId",ExecutiveId));
			ExecutiveTicketInfo executiveTicketInfo = (ExecutiveTicketInfo)c.list().get(0);
			executiveTicketInfo.setStatus(status);

			sessionFactory.getCurrentSession().update(executiveTicketInfo);
			
			Criteria c_ticketing = sessionFactory.getCurrentSession().createCriteria(Ticketing.class);
			c_ticketing.add(Restrictions.eq("ticketNum",ticketid));
			c.add(Restrictions.eq("executiveId",ExecutiveId));
			Ticketing ticketing = (Ticketing)c_ticketing.list().get(0);
			ticketing.setStatus(status);

			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}
	
	
	public Boolean update_comment(String ticketid,String status,String ExecutiveId) {
		try{

			Criteria c = sessionFactory.getCurrentSession().createCriteria(ExecutiveTicketInfo.class);
			c.add(Restrictions.eq("ticketNum",ticketid));
			c.add(Restrictions.eq("executiveId",ExecutiveId));
			ExecutiveTicketInfo executiveTicketInfo = (ExecutiveTicketInfo)c.list().get(0);
			executiveTicketInfo.setComments(status);

			sessionFactory.getCurrentSession().update(executiveTicketInfo);
			
			Criteria c_ticketing = sessionFactory.getCurrentSession().createCriteria(Ticketing.class);
			c_ticketing.add(Restrictions.eq("ticketNum",ticketid));
			c.add(Restrictions.eq("executiveId",ExecutiveId));
			Ticketing ticketing = (Ticketing)c_ticketing.list().get(0);
			ticketing.setComments(status);

			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}

	public Boolean insert_taginformation(Taginformation Tag_data) {
		try{
			sessionFactory.getCurrentSession().save(Tag_data);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public Boolean delete_and_insert_taginformation(String customerid) {
		try{
			Criteria c = sessionFactory.getCurrentSession().createCriteria(Taginformation.class);
			c.add(Restrictions.eq("customerid",customerid));
			List<Object> oldtag_data = c.list();			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			final Taginformation_history old_data_backup = mapper.convertValue(oldtag_data.get(0),Taginformation_history.class);			
			sessionFactory.getCurrentSession().save(old_data_backup);			
			sessionFactory.getCurrentSession().delete(mapper.convertValue(oldtag_data.get(0),Taginformation.class));			
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public List<Taginformation> get_taginformation(String customerid) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Taginformation.class);
		try{
			c.add(Restrictions.eq("customerid",customerid));					
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return c.list();
	}

	public Boolean delete_and_insert_taginformation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<User> get_mail(String region)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("type","Admin"));
		c.add(Restrictions.eq("region",region));
		List<User> obj =  c.list();
		return obj;
		
	}
	
	public String sendEmail(final String mailid,final String ticketid) throws MessagingException {
	     
      mailSender.send(new MimeMessagePreparator() {
    	  public void prepare(MimeMessage mimeMessage) throws MessagingException {
    		  

    	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    	    message.setTo(mailid);
    	    message.setSubject("Ticket Information");	    	 
    	    message.setText("Dear <b>" + "Admin" +"</b> ,<br><br> Ticket with Id <b>" +ticketid+" </b> is Raised. <br><br> Please <a href='http://ctoceu.cyient.com:3290/RFIDAssetTracking/'>login</a> for other details", true);
    	    
    	  }
    	});
		
		return "Mail Sent";
	}
	
}