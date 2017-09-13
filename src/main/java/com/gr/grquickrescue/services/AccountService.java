package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.gr.grquickrescue.dao.AccountDao;
import com.gr.grquickrescue.dao.ContactDao;
import com.gr.grquickrescue.dao.ContractDao;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Account;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.Contract;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountService implements AccountServiceRemote {
	
	private static AccountDao accountDao;
	private static ContractDao contractDao;
	private static ContactDao contactDao;

	
	public AccountService() {
		accountDao = (AccountDao) DaoManager.getInstance(AccountDao.class.getName());
		contractDao = (ContractDao) DaoManager.getInstance(ContractDao.class.getName());
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void saveAccount(Account account) {
		Contract contract = new Contract(100, 50, "sdfate", "edate", account);
		
		try {
			accountDao.saveInstance(account);
			contractDao.saveInstance(contract);
		}catch (Exception  e) {
			e.printStackTrace();
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateAccount(Account instance) {
		try {
			accountDao.updateInstance(instance);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	public Account findAccountById(Integer id) {
		
		Account account = null;
		try {
			account = accountDao.findInstanceById(id, Account.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return account;
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAccount(int id) {
		try {
			Account account = accountDao.findInstanceById(id, Account.class);
			accountDao.deleteInstance(account);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public List<Account> findAllAccounts() {

		List<Account> accounts = accountDao.findAllInstances(Account.class);
		return accounts;
	}
	@Override
	public Contract getActiveContract(int accountId) {
		List<Contract> contracts = contractDao.findContractsByAccountId(accountId);
		Contract activeContract = null;
		for (Contract contract : contracts) {
			if(contract.isActive()) {
				activeContract = contract;
				break;
			}
		}
		return activeContract;
	}
	@Override
	public int getTotalLogin(int accountId) {
		
		int totalLogin = 0;
		try {
			List<Contact> contacts = contactDao.findContactsByAccountId(accountId);
			
			for (Contact contact : contacts) {
				if(contact.isHasLogin()) {
					totalLogin++;
				}
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		return totalLogin;
	}

	public static ContactDao getContactDao() {
		return contactDao;
	}

	public static void setContactDao(ContactDao contactDao) {
		AccountService.contactDao = contactDao;
	}
}