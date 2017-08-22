package com.gr.grquickrescue.servicestest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.Contract;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.AlertProfileServiceRemote;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.ContractServiceRemote;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

public class TestAddressServiceManager {

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
	public void TestSaveAddress() 
	{
		Account account = new  Account(21, "khan1","mibrahim@globalrescue.com","GMT5+");
		
		Account testAccount = remoteAccountService.findAccountById(21);
		Address address  = new Address("House No 1","DGK","Punjab","Pakistan");
		
		Contact contact   = new Contact("firstName1","lastName","mibrahim@globalrescue.com","Male","9347643236",true,address,testAccount);
		
		remoteAddressService.saveAddress(address);
		remoteAccountService.saveAccount(account);
		remoteContactService.saveContact(contact);
	}

}
