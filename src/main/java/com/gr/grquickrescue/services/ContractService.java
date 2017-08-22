package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gr.grquickrescue.dao.ContractDao;
import com.gr.grquickrescue.dao.ContractDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Contract;

@Stateless
public class ContractService implements ContractServiceRemote {
	@EJB
	private static ContractDao contractDao;
	
	public ContractService() 
	{
		contractDao = (ContractDaoHibernateImpl)DaoManager.getInstance(ContractDao.class.getName());
	}
	@Override
	public void saveContract(Contract entity) {
		// TODO Auto-generated method stub
		contractDao.saveContract(entity);
	}

	@Override
	public void updateContract(Contract entity) {
		// TODO Auto-generated method stub
		contractDao.updateContract(entity);
	}

	@Override
	public Contract findContractById(int accountId) {
		// TODO Auto-generated method stub
		Contract contract = contractDao.findContractById(accountId);
		return contract;
	}

	@Override
	public void deleteContract(int accountId) {
		// TODO Auto-generated method stub
		Contract contract = contractDao.findContractById(accountId);
		contractDao.deleteContract(contract);
	}

	@Override
	public List<Contract> findAllContracts() {
		// TODO Auto-generated method stub
		return contractDao.findAllContracts();
	}
	@Override
	public List<Contract> findContractsByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return contractDao.findContractsByAccountId(accountId);
	}

}
