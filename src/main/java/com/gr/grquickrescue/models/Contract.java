package com.gr.grquickrescue.models;

public class Contract implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int maxContacts;
	private int maxLogins;
	private String startDate;
	private String endDate;
	private Account account;
	private boolean isEditable;
	public Contract(int maxCon , int maxLogin,String sdate,String edate, Account account) {
		
		setMaxContacts(maxCon);
		setMaxLogins(maxLogin);
		setStartDate(sdate);
		setEndDate(edate);
		setAccount(account);
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
	public String getStartDate() {
		return this.startDate;
	}
	public void setStartDate(String sDate) {
		this.startDate = sDate;
	}
	public String getEndDate() {
		return 	this.endDate;
	}
	public void setEndDate(String eDate) {
		this.endDate = eDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public boolean isEditable() {
		return isEditable;
	}
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
}