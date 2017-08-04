package com.gr.grquickrescue.daomanagertest;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.dao.AccountDao;
import com.gr.grquickrescue.dao.AccountDaoHibernateImpl;
import com.gr.grquickrescue.dao.AddressDao;
import com.gr.grquickrescue.dao.AddressDaoHibernateImpl;
import com.gr.grquickrescue.dao.ContactDao;
import com.gr.grquickrescue.dao.ContactDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;

public class TestDaoManager {
	
	private AddressDaoHibernateImpl addressDao;
	private AccountDaoHibernateImpl accountDao;
	private ContactDaoHibernateImpl contactDao;
	@Before
	public void init() 
	{
		addressDao = (AddressDaoHibernateImpl)DaoManager.getInstance(AddressDao.class.getName());
		accountDao = (AccountDaoHibernateImpl)DaoManager.getInstance(AccountDao.class.getName());
		contactDao = (ContactDaoHibernateImpl)DaoManager.getInstance(ContactDao.class.getName());
	}
	@Test
	public void TestSaveAddress() {
		
		Address addr  = new Address(1,"House No 65","DGK","Punjab","Pakistan");
		addressDao.openCurrentSessionwithTransaction();
		addressDao.saveAddress(addr);
		addressDao.closeCurrentSessionwithTransaction();
		
	}
	@Test
	public void TestSaveAccount() 
	{
		
		Account acc = new  Account(1, "khan","mibrahim@globalrescue.com","GMT5+");
		accountDao.openCurrentSessionwithTransaction();
		accountDao.saveAccount(acc);
		accountDao.closeCurrentSessionwithTransaction();
		
	}
	@Test
	public void TestSaveContact() 
	{
		Address addr  = new Address(2,"House No 65","DGK","Punjab","Pakistan");
		addressDao.openCurrentSessionwithTransaction();
		addressDao.saveAddress(addr);
		addressDao.closeCurrentSessionwithTransaction();
		
		Account acc = new  Account(2, "khan","mibrahim@globalrescue.com","GMT5+");
		accountDao.openCurrentSessionwithTransaction();
		accountDao.saveAccount(acc);
		accountDao.closeCurrentSessionwithTransaction();
		
		Contact con   = new Contact(1,"firstName","lastName","mibrahim@globalrescue.com","Male","9347643236",true,addr,acc);
		contactDao.openCurrentSessionwithTransaction();
		contactDao.saveContact(con);
		contactDao.closeCurrentSessionwithTransaction();
		
	}
}
