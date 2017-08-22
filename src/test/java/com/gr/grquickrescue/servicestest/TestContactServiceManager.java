package com.gr.grquickrescue.servicestest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

public class TestContactServiceManager {

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
	public void TestSaveContactService() 
	{
		Address addr  = new Address("House No 65","DGK","Punjab","Pakistan");
		remoteAddressService.saveAddress(addr);

		
		Account acc = new Account(22, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc);
		
		Contact con   = new Contact("firstName","lastName","mibrahim@globalrescue.com","Male","9347643236",true,addr,acc);
		remoteContactService.saveContact(con);
	}


}
