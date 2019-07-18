package com.cyient.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyient.model.Customer;
import com.cyient.model.Design;
import com.cyient.model.Exchange;
import com.cyient.model.Executive;
import com.cyient.model.ExecutiveTicketInfo;
import com.cyient.model.Headers_keys;
import com.cyient.model.Inventory;
import com.cyient.model.Ticketing;
import com.cyient.model.User;
import com.google.gson.Gson;


@Repository
public class RFIDDAOImpl implements RFIDDAO {

	@Autowired
	private SessionFactory sessionFactory;

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
	
	
	public void addExchange(Exchange exchange) {
		sessionFactory.getCurrentSession().saveOrUpdate(exchange);
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
	
	
	public List<ExecutiveTicketInfo>getTickets_based_on_Executive(String Executive_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(ExecutiveTicketInfo.class);
        c.add(Restrictions.eq("executiveId",Executive_id));
        c.add(Restrictions.eq("status","OPEN"));

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
	
	public List<Inventory> getInventory(String customer_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Inventory.class);
		Customer object = new Customer();
		object.setCustomerId(customer_id);
        c.add(Restrictions.eq("customerId",object));

		return (List<Inventory>)c.list();
		
	}
	
	public List<Design> getDesign(String customer_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Design.class);
		Customer object = new Customer();
		object.setCustomerId(customer_id);
        c.add(Restrictions.eq("customerId",object));

		return (List<Design>)c.list();
	}

	public List<Customer> getCustomer(String customer_id)
	{
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Customer.class);
        c.add(Restrictions.eq("customerId",customer_id));
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
			sessionFactory.getCurrentSession().update(customer);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}
	
	public Boolean update_design(Design design) {
		try{
			sessionFactory.getCurrentSession().update(design);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}
	
	public Boolean update_inventory(Inventory inventory) {
		try{
			sessionFactory.getCurrentSession().update(inventory);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;

		}

	}
	
	
}