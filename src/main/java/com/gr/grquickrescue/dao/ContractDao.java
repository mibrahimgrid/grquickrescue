package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Contract;

@Remote
public interface ContractDao {

	public void saveContract(Contract Entity);

	public void updateContract(Contract Entity);

	public Contract findContractById(int id);
	
	public void deleteContract(Contract Entity);

	public List<Contract> findAllContracts();
	
	public List<Contract> findContractsByAccountId(int accountId);

	
}
