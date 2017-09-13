package com.gr.grquickrescue.dao;

import java.util.List;
import com.gr.grquickrescue.models.Contract;

public class ContractDaoHibernateImpl extends GenericDaoHibernateImpl<Contract, Integer> implements ContractDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<Contract> findContractsByAccountId(int accountId) {
		openCurrentSession();
		List<Contract> contracts = (List<Contract>) getCurrentSession().createQuery("from Contract where accountFK = "+ accountId).list();
		closeCurrentSession();
		return contracts;
	}
}