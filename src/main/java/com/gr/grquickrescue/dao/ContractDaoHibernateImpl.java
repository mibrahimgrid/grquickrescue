package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.gr.grquickrescue.models.Contract;
@Stateless
public class ContractDaoHibernateImpl implements ContractDao{

	private Session currentSession;
	private Transaction currentTransaction;

	public Session openCurrentSession()
	{
		
		currentSession = (Session) getSessionFactory().openSession();
		return currentSession;
		
	}
	public Session openCurrentSessionwithTransaction() 
	{
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	public void closeCurrentSession() 
	{
		currentSession.close();
		
	}
	public void closeCurrentSessionwithTransaction()
	{
		currentTransaction.commit();
		
	}
	private static SessionFactory getSessionFactory() 
	{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	public Session getCurrentSession() 
	{
		return currentSession;
	}
	public void setCurrentSession(Session currentSession)
	{
		this.currentSession = currentSession;
	}
	public Transaction getCurrentTransaction()
	{
		return currentTransaction;
	}
	public void setCurrentTransaction(Transaction currentTransaction) 
	{
		this.currentTransaction = currentTransaction;
	}
	
	@Override
	public void saveContract(Contract entity) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().save(entity);
		closeCurrentSessionwithTransaction();
	}

	@Override
	public void updateContract(Contract entity) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().update(entity);
		closeCurrentSessionwithTransaction();
	}

	@Override
	public Contract findContractById(int id) {
		// TODO Auto-generated method stub
		openCurrentSession();
		Contract contract = (Contract) getCurrentSession().get(Contract.class, id);
		closeCurrentSession();
		return contract;
	}

	@Override
	public void deleteContract(Contract entity) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(entity);
		closeCurrentSessionwithTransaction();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Contract> findAllContracts() {
		// TODO Auto-generated method stub
		openCurrentSession();
		List<Contract> contracts = (List<Contract>) getCurrentSession().createQuery("from Contract").list();
		closeCurrentSession();
		return contracts;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Contract> findContractsByAccountId(int accountId) {
		// TODO Auto-generated method stub
		openCurrentSession();
		List<Contract> contracts = (List<Contract>) getCurrentSession().createQuery("from Contract where accountFK = "+ accountId).list();
		closeCurrentSession();
		return contracts;
	}

}
