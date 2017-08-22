package com.gr.grquickrescue.servicestest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Contract;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.ContractServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

public class TestContractServiceManager {

	private AccountServiceRemote remoteAccountService;
	private ContractServiceRemote remoteContractService;
	
	@Before
	public void init() 
	{
		remoteContractService= (ContractServiceRemote)ServiceManager.getInstance(ContractServiceRemote.class.getName());
		remoteAccountService = (AccountServiceRemote) ServiceManager.getInstance(AccountServiceRemote.class.getName());
	}
	@Test
	public void TestSaveContract() 
	{
		Account account = new Account(123, "TestSaveContract", "TestEmail", "GMT");
		remoteAccountService.saveAccount(account);
		
		Contract contract = new Contract(5, 5, "05-05-2017", "09-08-2017", account);
		
		remoteContractService.saveContract(contract);
	}
}
