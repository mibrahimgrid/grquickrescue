package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Account;
@Remote
public interface AccountServiceRemote{

	public void saveAccount(Account entity);
	public void updateAccount(Account entity);
	public Account findAccountById(int accountId);
	public void deleteAccount(int accountId);
	public List<Account> findAllAccounts();
	public void deleteAllAccounts();
}
