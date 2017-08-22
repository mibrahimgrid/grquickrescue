package com.gr.grquickrescue.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
@ManagedBean
@SessionScoped
public class AccountController extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private AccountServiceRemote accountService;
	private List<Account> accountsList;

	@PostConstruct
	public void init()
	{
		accountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		updateAccountsList();
	}
	public void updateAccountsList() 
	{
		accountsList = accountService.findAllAccounts();
	}
	public List<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;
	}
	public String updateAccount(Account account) 
	{
		account.setEditable(true);
		return null;
	}
	public void deleteAccount(Account account) 
	{
		accountService.deleteAccount(account.getId());
		updateAccountsList();
	}
	public String saveEdit(Account account1) 
	{
		for (Account account : accountsList) {
			account.setEditable(false);
		}
		accountService.updateAccount(account1);
		updateAccountsList();
		return null;
	}
	public void cancelEdit(Account account) 
	{
		account.setEditable(false);
	}
	public void addAccount() 
	{
		Account account  = new Account();
		account.setName(this.getName());
		account.setEmail(this.getEmail());
		account.setTimeZone(this.getTimeZone());
		
		accountService.saveAccount(account);
		updateAccountsList();
	}
}	
