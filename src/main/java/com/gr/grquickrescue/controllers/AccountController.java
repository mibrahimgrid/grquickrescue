package com.gr.grquickrescue.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
@ManagedBean
@RequestScoped
public class AccountController {

	@EJB
	private AccountServiceRemote accountService;
	
	private List<Account> accountsList;

	@PostConstruct
	public void init() 
	{
		accountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		accountsList  = accountService.findAllAccounts();
	}
	
	public List<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;
	}
}
