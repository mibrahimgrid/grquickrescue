package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Contact;
@Remote
public interface ContactServiceRemote {

	public void saveContact(Contact entity);
	public void updateContact(Contact entity);
	public Contact findContactById(int accountId);
	public void deleteContact(int accountId);
	public List<Contact> findAllContacts();
	public void deleteAllContacts();
}
