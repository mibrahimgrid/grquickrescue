package com.gr.grquickrescue.daomanagertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.dao.AccountDao;
import com.gr.grquickrescue.dao.AccountDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Account;

public class TestAccountDaoManager {

	private AccountDao accountDao;
	
	@Before
	public void init() 
	{
		accountDao = (AccountDaoHibernateImpl)DaoManager.getInstance(AccountDao.class.getName());
	}
	@Test
	public void TestSaveAccount() 
	{
		
		Account acc = new  Account(1, "khan","mibrahim@globalrescue.com","GMT5+");
		accountDao.saveAccount(acc);
		
	}
}
