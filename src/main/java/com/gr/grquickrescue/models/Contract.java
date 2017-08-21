package com.gr.grquickrescue.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Contract implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int maxContacts;
	private int maxLogins;
	private Date startDate;
	private Date endDate;
	public Contract(int maxCon , int maxLogin,String sdate,String edate) {
		
		setMaxContacts(maxCon);
		setMaxLogins(maxLogin);
		setStartDate(sdate);
		setEndDate(edate);
	}
	public Contract() 
	{
	}
	
	public int getMaxContacts() {
		return maxContacts;
	}
	public void setMaxContacts(int maxContacts) {
		this.maxContacts = maxContacts;
	}
	public int getMaxLogins() {
		return maxLogins;
	}
	public void setMaxLogins(int maxLogins) {
		this.maxLogins = maxLogins;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(String sDate) {
		try {
			this.startDate = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH).parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getEndDate() {
		return 	this.endDate;
	}
	public void setEndDate(String eDate) {
		try {
			this.endDate = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH).parse(eDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}