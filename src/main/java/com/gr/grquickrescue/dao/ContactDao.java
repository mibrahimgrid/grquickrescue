package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Contact;
@Remote
public interface ContactDao 
{
	public void saveContact(Contact Entity);

	public void updateContact(Contact Entity);

	public Contact findContactById(int id);

	public void deleteContact(Contact Entity);

	public List<Contact> findAllContacts();

	public void deleteAllContacts();
}
