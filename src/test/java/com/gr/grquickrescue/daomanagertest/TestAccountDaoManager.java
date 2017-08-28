package com.gr.grquickrescue.daomanagertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.gr.grquickrescue.dao.AccountDao;
import com.gr.grquickrescue.dao.AccountDaoHibernateImpl;
import com.gr.grquickrescue.dao.ContractDao;
import com.gr.grquickrescue.dao.ContractDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Contract;

public class TestAccountDaoManager {

	private AccountDao accountDao;
	private ContractDao contractDao;
	
	@Before
	public void init() 
	{
		accountDao = (AccountDaoHibernateImpl)DaoManager.getInstance(AccountDao.class.getName());
		contractDao = (ContractDaoHibernateImpl)DaoManager.getInstance(ContractDao.class.getName());
		
	}
	@Test
	public void TestSaveAccount() 
	{
		
		Account acc = new  Account(1, "khan","mibrahim@globalrescue.com","UTC+5");
		accountDao.saveAccount(acc);
		
		Contract contract  = new Contract(100,50,"12-12-2016","12-12-2017",acc);
		contractDao.saveContract(contract);
		
	}
}
