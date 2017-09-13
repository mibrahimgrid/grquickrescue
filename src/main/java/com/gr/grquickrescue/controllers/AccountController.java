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
	private boolean renderDiv;
	@PostConstruct
	public void init() {
		renderDiv = false;
		accountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		updateAccountsList();
	}
	public void emptydiv() {
		this.setEmail("");
		this.setName("");
		this.setTimeZone("");
	}
	public void openNewAccountDiv() {
		renderDiv = true;
	}
	public void closeNewAccountDiv() {
		setRenderDiv(false);
		emptydiv();
	} 
	public String addAccount() {

		Account account = new Account();
		account.setName(this.getName());
		account.setEmail(this.getEmail());
		account.setTimeZone(this.getTimeZone());
		accountService.saveAccount(account);
		this.setName("");
		this.setEmail("");
		this.setTimeZone("");
		closeNewAccountDiv();
		updateAccountsList();
		return NavigationController.gotoAccounts(true);
	}
	public String cancel() {
		this.setName("");
		this.setEmail("");
		this.setTimeZone("");
		return null;
	}
	public String makeEditable(Account account) {
		account.setEditable(true);
		return null;
	}

	public void deleteAccount(Account account) {
		accountService.deleteAccount(account.getId());
		updateAccountsList();
	}

	public String updateaccount(Account account1) {
		for (Account account : accountsList) {
			account.setEditable(false);
		}
		accountService.updateAccount(account1);
		updateAccountsList();
		return null;
	}

	public void cancelEdit(Account account) {
		account.setEditable(false);
	}

	public void updateAccountsList() {
		accountsList = accountService.findAllAccounts();
	}

	public List<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;
	}
	public boolean isRenderDiv() {
		return renderDiv;
	}
	public void setRenderDiv(boolean renderDiv) {
		this.renderDiv = renderDiv;
	}
}
