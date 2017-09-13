package com.gr.grquickrescue.dao;

import java.util.List;
import com.gr.grquickrescue.models.Contract;

public interface ContractDao extends GenericDao<Contract, Integer>{
	
	public List<Contract> findContractsByAccountId(int accountId);
}
