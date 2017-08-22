package com.gr.grquickrescue.servicestest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Contract;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.ContractServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

public class TestAccountServiceManager {

	private AccountServiceRemote remoteAccountService;
	private ContractServiceRemote remoteContractService;
	
	@Before
	public void init() 
	{
		remoteAccountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
		remoteContractService = (ContractServiceRemote) ServiceManager.getInstance(ContractServiceRemote.class.getName()); 
	}
	@Test
	public void TestSaveAccount() 
	{
		Account acc = new  Account(21, "khan","mibrahim@globalrescue.com","GMT5+");
		remoteAccountService.saveAccount(acc);
	}
	@Test
	public void TestDeleteAccount() 
	{
			Account account = new Account(123, "sada","asdsaf","dasda");
			remoteAccountService.saveAccount(account);

			Contract contract = new Contract(15,13,"02-04-2017","04-08-2017",account);
			
			remoteContractService.saveContract(contract);
			remoteAccountService.deleteAccount(account.getId());
	}
}
