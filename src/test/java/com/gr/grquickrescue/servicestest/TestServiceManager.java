package com.gr.grquickrescue.servicestest;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

public class TestServiceManager {

	private AddressServiceRemote remoteAddressService;
	private AccountServiceRemote remoteAccountService;
	private ContactServiceRemote remoteContactService;
	
	@Before
	public void init() 
	{
		remoteAddressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		remoteAccountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		remoteContactService = (ContactServiceRemote) ServiceManager.getInstance(ContactServiceRemote.class.getName());
	}
	
	@Test
	public void TestSaveAddressService() 
	{
		Address addr  = new Address(21,"House No 65","DGK","Punjab","Pakistan");
		remoteAddressService.saveAddress(addr);
	}
	@Test
	public void TestSaveAccountService() 
	{
		Account acc = new  Account(21, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc);
	}
	@Test
	public void TestSaveContactService() 
	{
		Address addr  = new Address(22,"House No 65","DGK","Punjab","Pakistan");
		remoteAddressService.saveAddress(addr);

		
		Account acc = new  Account(22, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc);
		
		Contact con   = new Contact(21,"firstName","lastName","mibrahim@globalrescue.com","Male","9347643236",true,addr,acc);
		remoteContactService.saveContact(con);
	}
}
