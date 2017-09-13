package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Contract;

@Remote
public interface AccountServiceRemote {

	public void saveAccount(Account entity);

	public void updateAccount(Account entity);

	public Account findAccountById(Integer accountId);

	public void deleteAccount(int accountId);

	public List<Account> findAllAccounts();
	
	public Contract getActiveContract(int accountId);
	
	public int getTotalLogin(int accountId);
}