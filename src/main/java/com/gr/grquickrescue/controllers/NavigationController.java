package com.gr.grquickrescue.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationController implements java.io.Serializable
{
	private static String accountsPath = "/resources/secured/account/account";
	private static String contactsPath = "/resources/secured/contact/contact";
	private static String alertProfilesPath = "/resources/secured/alertprofile/alertProfile";
	private static String contractsPath = "/resources/secured/contract/contract";
	private static String loginPath = "/login";
	private static String indexPath= "/index";
	private static String redirectTrueArgument = "?faces-redirect=true";
	private static final long serialVersionUID = 1L;

	public String gotoContracts(boolean redirect) 
	{
		return redirect ? contractsPath+redirectTrueArgument : contractsPath;
	}
	public String gotoAccounts(boolean redirect) 
	{
		return redirect ? accountsPath+redirectTrueArgument : accountsPath; 
	}
	public String gotoContacts(boolean redirect) 
	{
		return redirect ? contactsPath+redirectTrueArgument: contactsPath;
	}
	public String gotoIndex(boolean redirect) 
	{
		return redirect ? indexPath+redirectTrueArgument : indexPath;
	}
	public String gotoAlertProfiles(boolean redirect) 
	{
		return redirect ?  alertProfilesPath+redirectTrueArgument: alertProfilesPath;
	}
	public String goToLogin(boolean redirect) 
	{
		return redirect ? loginPath + redirectTrueArgument: loginPath;
	}
}