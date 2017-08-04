package com.gr.grquickrescue.dao;

import java.util.List;


import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.gr.grquickrescue.models.Address;

import javax.ejb.Stateless;
@Stateless
public class AddressDaoHibernateImpl implements AddressDao
{
	private Session currentSession;
	private Transaction currentTransaction;

	public Session openCurrentSession()
	{

		currentSession = getSessionFactory().openSession();
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
		currentSession.close();
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
	public void saveAddress(Address entity) 
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().save(entity);
		closeCurrentSessionwithTransaction();
	}
	public void updateAddress(Address entity)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().update(entity);
		closeCurrentSessionwithTransaction();
	}
	public Address findAddressById(int id) 
	{
		openCurrentSession();
		Address address = (Address) getCurrentSession().get(Address.class, id);
		closeCurrentSession();
		return address;
	}
	public void deleteAddress(Address entity)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(entity);
		closeCurrentSessionwithTransaction();
	}
	@SuppressWarnings("unchecked")
	public List<Address> findAllAddresses() 
	{
		openCurrentSession();
		List<Address> addresses = (List<Address>) getCurrentSession().createQuery("from Address").list();
		closeCurrentSession();
		return addresses;
	}
	public void deleteAllAddresses() 
	{
		openCurrentSessionwithTransaction();
		List<Address> entityList = findAllAddresses();
		for (Address entity : entityList) 
		{
			deleteAddress(entity);
		}
		closeCurrentSessionwithTransaction();
	}

}
