package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.gr.grquickrescue.models.AlertProfile;
@Stateless
public class AlertProfileDaoHibernateImpl implements AlertProfileDao{

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
	public void saveAlertProfile(AlertProfile entity) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().save(entity);
		closeCurrentSessionwithTransaction();
	}

	@Override
	public void updateAlertProfile(AlertProfile entity) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().update(entity);
		closeCurrentSessionwithTransaction();
	}

	@Override
	public AlertProfile findAlertProfileById(int id) {
		// TODO Auto-generated method stub
		openCurrentSession();
		AlertProfile alert = (AlertProfile) getCurrentSession().get(AlertProfile.class, id);
		closeCurrentSession();
		return alert;
	}

	@Override
	public void deleteAlertProfile(AlertProfile entity) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(entity);
		closeCurrentSessionwithTransaction();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AlertProfile> findAllAlertProfiles() {
		// TODO Auto-generated method stub
		openCurrentSession();
		List<AlertProfile> alerts = (List<AlertProfile>) getCurrentSession().createQuery("from AlertProfile").list();
		closeCurrentSession();
		return alerts;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AlertProfile> findAlertProfilesByAccountId(int id)
	{
		openCurrentSession();
		List<AlertProfile> accountAlerts = (List<AlertProfile>)getCurrentSession().createQuery("from AlertProfile where accountIDFK = "+id).list(); 
		closeCurrentSession();
		return accountAlerts;
	}
	@Override
	public void deleteAllAlertProfiles() {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		List<AlertProfile> entityList = findAllAlertProfiles();
		for (AlertProfile entity : entityList) 
		{
			deleteAlertProfile(entity);
		}
		closeCurrentSessionwithTransaction();
	}

}