package com.gr.grquickrescue.daomanagertest;

import static org.junit.Assert.*;

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

public class TestContactDaoManager {

	private AddressDao addressDao;
	private AccountDao accountDao;
	private ContactDao contactDao;
	@Before
	public void init() 
	{
		addressDao = (AddressDaoHibernateImpl)DaoManager.getInstance(AddressDao.class.getName());
		accountDao = (AccountDaoHibernateImpl)DaoManager.getInstance(AccountDao.class.getName());
		contactDao = (ContactDaoHibernateImpl)DaoManager.getInstance(ContactDao.class.getName());
	}
	@Test
	public void TestSaveContact() 
	{
		Address addr  = new Address("House No 65","DGK","Punjab","Pakistan");
		addressDao.saveAddress(addr);
		
		Account acc = new  Account(2, "khan","mibrahim@globalrescue.com","GMT5+");
		accountDao.saveAccount(acc);
		
		Contact con   = new Contact("firstName","lastName","mibrahim@globalrescue.com","Male","9347643236",true,addr,acc);
		contactDao.saveContact(con);
		
	}

}
