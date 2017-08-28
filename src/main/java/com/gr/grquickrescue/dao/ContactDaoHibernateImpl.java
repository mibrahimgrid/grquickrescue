package com.gr.grquickrescue.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.gr.grquickrescue.models.*;
import javax.ejb.Stateless;

@Stateless
public class ContactDaoHibernateImpl implements ContactDao
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
	public void saveContact(Contact entity)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().save(entity);
		closeCurrentSessionwithTransaction();
	}
	public void updateContact(Contact entity)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().update(entity);
		closeCurrentSessionwithTransaction();
	}
	public Contact findContactById(int id) 
	{
		openCurrentSession();
		Contact contact = (Contact) getCurrentSession().get(Contact.class, id);
		closeCurrentSession();
		return contact;
	}
	@Override
	public Contact findContactByEmail(String email) {
		openCurrentSession();
		Contact contact = (Contact) getCurrentSession().createQuery("from Contact where emailAddress = '"+email+"'").uniqueResult();
		closeCurrentSession();
		return contact;
	}
	public void deleteContact(Contact entity)
	{
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(entity);
		closeCurrentSessionwithTransaction();
	}
	@SuppressWarnings("unchecked")
	public List<Contact> findAllContacts() 
	{
		openCurrentSession();
		List<Contact> contacts = (List<Contact>) getCurrentSession().createQuery("from Contact").list();
		closeCurrentSession();
		return contacts;
	}
	@SuppressWarnings("unchecked")
	public List<Contact> findContactsByAccountId(int accountId)
	{
		openCurrentSession();
		List<Contact> contacts = (List<Contact>)getCurrentSession().createQuery("from Contact where accountID = "+accountId).list();
		closeCurrentSession();
		return contacts;
	}
	public void deleteAllContacts() 
	{
		openCurrentSessionwithTransaction();
		List<Contact> entityList = findAllContacts();
		for (Contact entity : entityList) 
		{
			deleteContact(entity);
		}
		closeCurrentSessionwithTransaction();
	}
	
}

