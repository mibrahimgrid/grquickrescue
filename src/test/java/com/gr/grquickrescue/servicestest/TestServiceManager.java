package com.gr.grquickrescue.servicestest;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.AlertProfile;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.Contract;
import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.AlertProfileServiceRemote;
import com.gr.grquickrescue.services.ContactServiceRemote;
import com.gr.grquickrescue.services.ContractServiceRemote;
import com.gr.grquickrescue.services.QRLoginServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.PasswordUtility;

public class TestServiceManager {

	private AddressServiceRemote remoteAddressService;
	private AccountServiceRemote remoteAccountService;
	private ContactServiceRemote remoteContactService;
	private QRLoginServiceRemote remoteQRLoginService;
	private ContractServiceRemote remoteContractService;
	private AlertProfileServiceRemote remoteAlertProfileService;
	@Before
	public void init() 
	{
		remoteContractService= (ContractServiceRemote)ServiceManager.getInstance(ContractServiceRemote.class.getName());
		remoteAlertProfileService = (AlertProfileServiceRemote) ServiceManager.getInstance(AlertProfileServiceRemote.class.getName());
		remoteAddressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		remoteAccountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		remoteContactService = (ContactServiceRemote) ServiceManager.getInstance(ContactServiceRemote.class.getName());
		remoteQRLoginService = (QRLoginServiceRemote) ServiceManager.getInstance(QRLoginServiceRemote.class.getName());
	}
	
	@Test
	public void TestSaveAddressService() 
	{
		Address address  = new Address("House No 1","DGK","Punjab","Pakistan");
		Contract contract = new Contract(20,10,"17-08-2017","18-08-2017");
		Account acc = new  Account(21, "khan1","mibrahim@globalrescue.com","GMT5+");
		Contact con   = new Contact("firstName1","lastName","mibrahim@globalrescue.com","Male","9347643236",true,address,acc);
		
		remoteAddressService.saveAddress(address);
		remoteContractService.saveContract(contract);
		remoteAccountService.saveAccount(acc);
		remoteContactService.saveContact(con);
		
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
		Address addr  = new Address("House No 65","DGK","Punjab","Pakistan");
		remoteAddressService.saveAddress(addr);

		
		Account acc = new Account(22, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc);
		
		Contact con   = new Contact("firstName","lastName","mibrahim@globalrescue.com","Male","9347643236",true,addr,acc);
		remoteContactService.saveContact(con);
	}
	@Test
	public void TestSaveQRLoginService() 
	{
		QRLogin login = new QRLogin();
		login.setUsername("khan");
		login.setPassword(PasswordUtility.generateRandomPassword());
		remoteQRLoginService.saveLogin(login);
		
		QRLogin testLogin = remoteQRLoginService.findLoginByUsername("khan");
		System.out.println("======================================================================================================================================");
		System.out.println(testLogin.getUsername() +"----"+ testLogin.getPassword());
		System.out.println("======================================================================================================================================");
	}
	@Test
	public void TestSvaeAlertProfile() 
	{
		Address addr  = new Address("House No 65","DGK","Punjab","Pakistan");
		remoteAddressService.saveAddress(addr);

		
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
	@Test
	public void TestSaveContract()
	{
		Contract contract = new Contract(10,20,"17-08-2017","18-08-2017");
		remoteContractService.saveContract(contract);
		
		Account acc2 = new Account(32786, "khan","mibrahim@globalrescue.com","GMT5+");
		
		remoteAccountService.saveAccount(acc2);
		acc2.setContract(contract);
	}

	
}
