package com.gr.grquickrescue.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.HttpSessionUtility;

@ManagedBean
@SessionScoped
public class LoginBean extends QRLogin
{
	
	private static final long serialVersionUID = 1L;
	@EJB
	private QRLoginServiceRemote loginService;
	@EJB
	private ContactServiceRemote contactService;

	@ManagedProperty(value="#{navigationController}")
	private NavigationController navigationController;
	
	private boolean loggedIn;
	private boolean qrContact;
	private Contact contact;

	@PostConstruct
	public void init() 
	{
		loggedIn = false;
		navigationController = new NavigationController();
		loginService = (QRLoginServiceRemote) ServiceManager.getInstance(QRLoginServiceRemote.class.getName());
		contactService = (ContactServiceRemote) ServiceManager.getInstance(ContactServiceRemote.class.getName());
	}

	public String doLogin() 
	{
		String returnPage = null;
		try {
			boolean isValidUser = verifyUser();
			
			if( isValidUser)  // if a valid user then add it to session and login
			{
				HttpSession session = HttpSessionUtility.getSession();
				qrContact = isQRContact(this.getUsername());
				this.loggedIn = true;
				session.setAttribute("isQRContact", qrContact);
				session.setAttribute("user", this.getUsername());
				session.setAttribute("accountId", contact.getAccount().getId());

				if(qrContact) 
				{
					returnPage = NavigationController.gotoAccounts(true);//getNavigationController().gotoAccounts(true);
				}else 
				{
					returnPage = getNavigationController().gotoContacts(true);
				}
			} else 			// if not a valid user send message
			{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Incorrect Username and Passowrd","Please enter correct username and Password"));
				this.loggedIn = false;
				returnPage = null;
			}
		} catch (Exception e) {
			returnPage = null;
		}
		return returnPage;
	}
	public String doLogout() 
	{
		HttpSession session = HttpSessionUtility.getSession();
	    session.invalidate();
	    loggedIn = false;
		
		return getNavigationController().goToLogin(false);
	}
	public boolean verifyUser() 
	{
		QRLogin loginUser = loginService.findLoginByUsername(this.getUsername());
		
		if(loginUser != null && loginUser.getPassword().equals(this.getPassword()) && loginUser.getUsername().equals(this.getUsername()) ) 
		{
			this.setContact( contactService.findContactByEmail(this.getUsername()));
			return true;
		}else 
		{
			return false;
		} 
	}
	public boolean isQRContact(String email) 
	{
		return email.indexOf("@quickrescue") >=0 ? true:false;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public NavigationController getNavigationController() {
		return navigationController;
	}

	public void setNavigationController(NavigationController navigationController) {
		this.navigationController = navigationController;
	}

	public boolean isQrContact() {
		return qrContact;
	}

	public void setQrContact(boolean qrContact) {
		this.qrContact = qrContact;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
