package com.gr.grquickrescue.dao;

import java.util.List;
import com.gr.grquickrescue.models.Contact;

public interface ContactDao extends GenericDao<Contact, Integer>
{
	

	public List<Contact> findContactsByAccountId(int accountId);
	
	public Contact findContactByEmail(String emailAddress) ;
	
}
