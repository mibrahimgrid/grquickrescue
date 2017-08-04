package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Account;
@Remote
public interface AccountDao 
{
	public void saveAccount(Account Entity);

	public void updateAccount(Account Entity);

	public Account findAccountById(int id);

	public void deleteAccount(Account Entity);

	public List<Account> findAllAccounts();

	public void deleteAllAccounts();
}
