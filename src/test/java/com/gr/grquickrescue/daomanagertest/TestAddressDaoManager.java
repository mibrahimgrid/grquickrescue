package com.gr.grquickrescue.daomanagertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.dao.AddressDao;
import com.gr.grquickrescue.dao.AddressDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Address;

public class TestAddressDaoManager {

	private AddressDao addressDao;
	@Before
	public void init() 
	{
		addressDao = (AddressDaoHibernateImpl)DaoManager.getInstance(AddressDao.class.getName());
	}
	@Test
	public void TestSaveAddress() {
		
		Address addr  = new Address("House No 65","DGK","Punjab","Pakistan");
		addressDao.saveAddress(addr);
		
	}
	
}
