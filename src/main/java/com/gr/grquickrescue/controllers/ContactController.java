package com.gr.grquickrescue.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

@ManagedBean
@RequestScoped
public class ContactController {

	@EJB
	private ContactServiceRemote contactService;
	
	private List<Contact> contactsList;

	@PostConstruct
	public void init() 
	{
		contactService = (ContactServiceRemote) ServiceManager.getInstance(ContactServiceRemote.class.getName());
		contactsList = contactService.findAllContacts();
	}
	public List<Contact> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<Contact> contactsList) {
		this.contactsList = contactsList;
	}
}
