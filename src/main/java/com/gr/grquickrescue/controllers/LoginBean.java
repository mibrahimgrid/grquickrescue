package com.gr.grquickrescue.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

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
	private Boolean loggedIn;

	@ManagedProperty(value="#{navigationController}")
	private NavigationController navigationController;

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
		if( verifyUser()) 
		{
			this.loggedIn = true;
			returnPage = getNavigationController().gotoAccounts(false);
		} 
		else {
			this.loggedIn = false;
			returnPage = getNavigationController().gotoIndex(true);
		} 

		return returnPage;
	}
	public Boolean verifyUser() 
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
	public void doLogout() 
	{
		loggedIn = false;
		//navigate to login page
	}

	public Boolean isLoggedIn() {
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
}
