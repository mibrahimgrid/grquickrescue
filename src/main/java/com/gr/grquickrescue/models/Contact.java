package com.gr.grquickrescue.models;

public class Contact implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private boolean status;
	private Address address;
	private Account account;
	private String email;
	private transient boolean isEditable;
	private boolean hasLogin;
	public Contact()
	{
		address = new Address();
	}
	public Contact( String fname,String lname,String eaddr,String gender,String phnumber,boolean status, Address addr,Account acc)
	{
		this.firstName = fname;
		this.lastName = lname;
		this.email = eaddr;
		this.gender = gender;
		this.phoneNumber = phnumber;
		this.status = status;
		this.address = addr;
		this.account = acc;
	}
	public void ToString()
	{

		System.out.println(id+", "+firstName+" "+lastName+", "+email+", "+gender+", "+phoneNumber+","+status+","+address.getId()+","+account.getId());
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailAddr) {
		this.email = emailAddr;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public boolean isEditable() {
		return isEditable;
	}
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
	public boolean isHasLogin() {
		return hasLogin;
	}
	public void setHasLogin(boolean hasLogin) {
		this.hasLogin = hasLogin;
	}
}