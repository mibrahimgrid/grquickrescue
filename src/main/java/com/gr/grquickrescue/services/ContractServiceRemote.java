package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Contract;

@Remote
public interface ContractServiceRemote {
	public void saveContract(Contract entity);
	public void updateContract(Contract entity);
	public Contract findContractById(int accountId);
	public void deleteContract(int accountId);
	public List<Contract> findAllContracts();
	List<Contract> findContractsByAccountId(int accountId);
}
