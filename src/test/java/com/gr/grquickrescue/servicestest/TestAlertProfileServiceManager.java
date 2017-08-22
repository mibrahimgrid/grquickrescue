package com.gr.grquickrescue.servicestest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.AlertProfile;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.AlertProfileServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

public class TestAlertProfileServiceManager {

	private AddressServiceRemote remoteAddressService;
	private AccountServiceRemote remoteAccountService;
	private AlertProfileServiceRemote remoteAlertProfileService;
	
	@Before
	public void init() 
	{
		remoteAlertProfileService = (AlertProfileServiceRemote) ServiceManager.getInstance(AlertProfileServiceRemote.class.getName());
		remoteAddressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		remoteAccountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
	}
	@Test
	public void TestSaveAlertProfile() 
	{
		Account acc = new Account(32786, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc);

		Account acc1 = new Account(32786, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc1);
		
		Account acc2 = new Account(32786, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc2);
		
		AlertProfile alert = new AlertProfile("test","dgk","pak",acc);
		remoteAlertProfileService.saveAlertProfile(alert);
		
		AlertProfile alert1 = new AlertProfile("test","dgk","pak",acc1);
		remoteAlertProfileService.saveAlertProfile(alert1);
		
		AlertProfile alert2 = new AlertProfile("test","dgk","pak",acc2);
		remoteAlertProfileService.saveAlertProfile(alert2);
		
	}

}