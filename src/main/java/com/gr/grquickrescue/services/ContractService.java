package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.gr.grquickrescue.dao.ContractDao;
import com.gr.grquickrescue.dao.ContractDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Contract;

@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class ContractService implements ContractServiceRemote {
	
	private static ContractDao contractDao;

	public ContractService() {
		contractDao = (ContractDaoHibernateImpl) DaoManager.getInstance(ContractDao.class.getName());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveContract(Contract entity) {
		try {
			contractDao.saveInstance(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateContract(Contract entity) {
		try {
			contractDao.updateInstance(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Contract findContractById(int accountId) {
		Contract contract = contractDao.findInstanceById(accountId, Contract.class);
		return contract;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteContract(int accountId) {
		try {
			Contract contract = contractDao.findInstanceById(accountId, Contract.class);
			contractDao.deleteInstance(contract);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Contract> findAllContracts() {
		return contractDao.findAllInstances(Contract.class);
	}

	@Override
	public List<Contract> findContractsByAccountId(int accountId) {
		return contractDao.findContractsByAccountId(accountId);
	}

}