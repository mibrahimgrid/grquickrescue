package com.gr.grquickrescue.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.PasswordUtility;
@ManagedBean
@SessionScoped
public class ContactController extends Contact {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ContactServiceRemote contactService;
	@EJB
	private AddressServiceRemote addressService;
	@EJB
	private AccountServiceRemote accountService;
	@EJB
	private QRLoginServiceRemote loginService;
	
	private List<Contact> contactsList;
	private List<Account> accountsList;
	
	
	
	@PostConstruct
	public void init() 
	{
		loginService = (QRLoginServiceRemote)ServiceManager.getInstance(QRLoginServiceRemote.class.getName());
		contactService = (ContactServiceRemote) ServiceManager.getInstance(ContactServiceRemote.class.getName());
		addressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		accountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
	}
	public String findContactsByAccountId(int accountId) 
	{
		this.setAccount(accountService.findAccountById(accountId));
		updateContactsList();
		return "/resources/secured/contact/contact";
	}
	public void updateContactsList() 
	{
		this.setContactsList(contactService.findContactsByAccountId(this.getAccount().getId()));
	}
	
	public String addNewContact() 
	{
		Address address  = new Address();
		
		address.setStreetAddress(this.getAddress().getStreetAddress());
		address.setCity(this.getAddress().getCity());
		address.setCountry(this.getAddress().getCountry());
		
		addressService.saveAddress(address);
		
		Contact contact = new Contact(this.getFirstName(),this.getLastName(),this.getEmail(),this.getGender(),this.getPhoneNumber(),this.isHasLogin(),address,this.getAccount());
		contact.setHasLogin(this.isHasLogin());
		contactService.saveContact(contact);
		if(isHasLogin()) 
		{
			QRLogin contactLogin = new QRLogin(this.getEmail(),PasswordUtility.generateRandomPassword());
			loginService.saveLogin(contactLogin);
		}
		updateContactsList();
		return null;
	}
	public String updateContact(Contact contact) 
	{
		contact.setEditable(true);
		return null;
	}
	public void deleteContact(Contact contact) 
	{
		contactService.deleteContact(contact.getId());
		updateContactsList();
	}
	public String saveEdit(Contact contact1) 
	{
		for (Contact contact : contactsList) {
			contact.setEditable(false);
		}
		contactService.updateContact(contact1);
		updateContactsList();
		return null;
	}
	public void cancelEdit(Contact contact) 
	{
		contact.setEditable(false);
	}
	public List<Account> getAccountsList() {
		return accountsList;
	}
	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;
	}
	public List<Contact> getContactsList() {
		return contactsList;
	}
	public void setContactsList(List<Contact> contactsList) {
		this.contactsList = contactsList;
	}
}
