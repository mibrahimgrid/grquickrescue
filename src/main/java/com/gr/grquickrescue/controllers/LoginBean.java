package com.gr.grquickrescue.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.SessionUtility;

@ManagedBean
@SessionScoped
public class LoginBean extends QRLogin
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private QRLoginServiceRemote loginService;

	@ManagedProperty(value="#{navigationController}")
	private NavigationController navigationController;
	
	private boolean loggedIn;
	private boolean qrContact;
	

	@PostConstruct
	public void init() 
	{
		loggedIn = false;
		navigationController = new NavigationController();
		loginService = (QRLoginServiceRemote) ServiceManager.getInstance(QRLoginServiceRemote.class.getName());
	}

	public String doLogin() 
	{
		String returnPage = null;
		boolean isValidUser = verifyUser();
		
		if( isValidUser)  // if a valid user then add it to session and login
		{
			HttpSession session = SessionUtility.getSession();
			this.setQrContact(isQrContact());
			this.loggedIn = true;
			session.setAttribute("isQRContact", qrContact);
			session.setAttribute("user", this.getUsername());
			if(qrContact) 
			{
				returnPage = getNavigationController().gotoAccounts(false);
			}else 
			{
				returnPage = getNavigationController().gotoContacts(false);
			}
			
		
		} else 			// if not a valid user send message
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd","Please enter correct username and Password"));
			this.loggedIn = false;
			returnPage = getNavigationController().gotoIndex(true);
		}
		return returnPage;
	}
	public String doLogout() 
	{
		HttpSession session = SessionUtility.getSession();
	    session.invalidate();
	    loggedIn = false;
		
		return getNavigationController().goToLogin(false);
	}
	public boolean verifyUser() 
	{
		QRLogin loginUser = loginService.findLoginByUsername(this.getUsername());

		if(loginUser != null && loginUser.getPassword().equals(this.getPassword()) && loginUser.getUsername().equals(this.getUsername()) ) 
		{
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
}
