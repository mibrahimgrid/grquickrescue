package com.gr.grquickrescue.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.gr.grquickrescue.models.Account;
import javax.ejb.Stateless;
@Stateless
public class AccountDaoHibernateImpl implements AccountDao
{
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
	public void saveAccount(Account entity) 
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().save(entity);
		closeCurrentSessionwithTransaction();
	}
	public void updateAccount(Account account)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().update(account);
		closeCurrentSessionwithTransaction();
	}
	public Account findAccountById(int id) 
	{
		openCurrentSession();
		Account account = (Account) getCurrentSession().get(Account.class, id);
		closeCurrentSession();
		return account;
	}
	public void deleteAccount(Account entity)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(entity);
		closeCurrentSessionwithTransaction();
	}
	@SuppressWarnings("unchecked")
	public List<Account> findAllAccounts() 
	{
		openCurrentSession();
		List<Account> accounts = (List<Account>) getCurrentSession().createQuery("from Account").list();
		closeCurrentSession();
		return accounts;
	}
	public void deleteAllAccounts() 
	{
		openCurrentSessionwithTransaction();
		List<Account> entityList = findAllAccounts();
		for (Account entity : entityList) 
		{
			deleteAccount(entity);
		}
		closeCurrentSessionwithTransaction();
	}

}

