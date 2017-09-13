package com.gr.grquickrescue.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.Contract;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.HttpSessionUtility;
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
	
	private List<Contact> contactsList;
	private boolean canAddMoreContacts;
	private boolean canAddMoreLogins;
	private boolean renderDiv;
	private boolean renderError;
	private String errorMessage;
	
	@PostConstruct
	public void init() 
	{
		renderDiv = false;
		renderError = false;
		errorMessage = "Can not add more contacts";
		contactService = (ContactServiceRemote) ServiceManager.getInstance(ContactServiceRemote.class.getName());
		addressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		accountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		this.setAccount(accountService.findAccountById((Integer)HttpSessionUtility.getSession().getAttribute("accountId")));
		HttpSessionUtility.getSession().setAttribute("MapAccountId", this.getAccount().getId());
		//updateContactsList();
	}
	
	public void openNewContactDiv() {
		renderDiv = true;
		if(!canAddMoreContacts) {
			renderError = true;
		}
	}
	public void emptyDiv() {
		this.setEmailAddress("");
		this.getAddress().setStreetAddress("");
		this.setFirstName("");
		this.setLastName("");
		this.getAddress().setCity("");
		this.getAddress().setCountry("");
		this.setPhoneNumber("");
		this.setGender("");
		this.setHasLogin(false);
	}
	public void closeNewContactDiv() {
		renderDiv = false;
		renderError = false;
		emptyDiv();
	}
	public String findContactsByAccountId(int accountId) 
	{
		this.setAccount(accountService.findAccountById(accountId));
		HttpSessionUtility.getSession().setAttribute("MapAccountId", accountId);
		updateContactsList();
		return "/resources/secured/contact/contact";
	}
	public void updateContactsList() 
	{
		this.setContactsList(contactService.findContactsByAccountId(this.getAccount().getId()));
		updateSubscription();
	}
	public void updateSubscription() {
		Contract activeContract =  accountService.getActiveContract(this.getAccount().getId());
		if( activeContract != null) {
			canAddMoreContacts = contactsList.size() < activeContract.getMaxContacts() ? true:false;
			canAddMoreLogins = accountService.getTotalLogin(this.getAccount().getId()) < activeContract.getMaxLogins()? true:false;
		}
	}
	public String addNewContact() 
	{
		Address address  = new Address();
		
		address.setStreetAddress(this.getAddress().getStreetAddress());
		address.setCity(this.getAddress().getCity());
		address.setCountry(this.getAddress().getCountry());
		
		addressService.saveAddress(address);
		
		Contact contact = new Contact(this.getFirstName(),this.getLastName(),this.getEmailAddress(),this.getGender(),this.getPhoneNumber(),this.isHasLogin(),true,address,this.getAccount());
		contactService.saveContact(contact);
		
		updateContactsList();
		closeNewContactDiv();
		
		return null;
	}
	public String makeContactEditable(Contact contact) 
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
	public List<Contact> getContactsList() {
		return contactsList;
	}
	public void setContactsList(List<Contact> contactsList) {
		this.contactsList = contactsList;
	}
	public boolean isCanAddMoreContacts() {
		return canAddMoreContacts;
	}
	public void setCanAddMoreContacts(boolean canAddMoreContacts) {
		this.canAddMoreContacts = canAddMoreContacts;
	}
	public boolean isCanAddMoreLogins() {
		return canAddMoreLogins;
	}
	public void setCanAddMoreLogins(boolean canAddMoreLogins) {
		this.canAddMoreLogins = canAddMoreLogins;
	}
	public boolean isRenderDiv() {
		return renderDiv;
	}
	public void setRenderDiv(boolean renderDiv) {
		this.renderDiv = renderDiv;
	}
	public boolean isRenderError() {
		return renderError;
	}
	public void setRenderError(boolean renderError) {
		this.renderError = renderError;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
