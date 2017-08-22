package com.gr.grquickrescue.models;

import java.util.Set;

public class Account implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String timeZone;
	private Set<Contract> contracts;
	private Set<Contact> contacts;
	private Set<AlertProfile> alertProfiles;
	private transient boolean isEditable;

	public Account(int id, String name, String email, String timezone) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.timeZone = timezone;
	}

	public Account() {
	}

	public void ToString() {
		System.out.println(id + ", " + name + ", " + email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public Set<AlertProfile> getAlertProfiles() {
		return alertProfiles;
	}

	public void setAlertProfiles(Set<AlertProfile> alertProfiles) {
		this.alertProfiles = alertProfiles;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

}
