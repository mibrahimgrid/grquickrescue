package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gr.grquickrescue.dao.AccountDao;
import com.gr.grquickrescue.dao.AccountDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Account;
@Stateless
public class AccountService implements AccountServiceRemote
{
	@EJB
	private static AccountDao accountDao;

	public AccountService()
	{
		accountDao = (AccountDaoHibernateImpl)DaoManager.getInstance(AccountDao.class.getName());
	}
	@Override
	public void saveAccount(Account entity)
	{
		accountDao.saveAccount(entity);
	}
	@Override
	public void updateAccount(Account entity)
	{

		accountDao.updateAccount(entity);
	}
	@Override
	public Account findAccountById(int id)
	{
		Account account = accountDao.findAccountById(id);
		return account;
	}
	@Override
	public void deleteAccount(int id)
	{

		Account account = accountDao.findAccountById(id);
		accountDao.deleteAccount(account);
	}
	@Override
	public List<Account> findAllAccounts()
	{

		List<Account> accounts = accountDao.findAllAccounts();
		return accounts;
	}
	@Override
	public void deleteAllAccounts()
	{
		accountDao.deleteAllAccounts();
	}
}
