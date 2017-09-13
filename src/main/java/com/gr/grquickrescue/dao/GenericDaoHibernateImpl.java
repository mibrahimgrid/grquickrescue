package com.gr.grquickrescue.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class GenericDaoHibernateImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	private Session currentSession;
	private Transaction currentTransaction;

	public Session openCurrentSession() {

		currentSession = (Session) getSessionFactory().openSession();
		return currentSession;

	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();

	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();

	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	@Override
	public void saveInstance(T instance) {
		openCurrentSessionwithTransaction();
		getCurrentSession().save(instance);
		closeCurrentSessionwithTransaction();
		
	}

	@Override
	public void updateInstance(T instance) {
		openCurrentSessionwithTransaction();
		getCurrentSession().update(instance);
		closeCurrentSessionwithTransaction();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findInstanceById(ID id, final Class<T> type) {
		openCurrentSession();
		T instance = (T) getCurrentSession().get(type, id);
		closeCurrentSession();
		return instance;
	}

	@Override
	public void deleteInstance(T instance) {
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(instance);
		closeCurrentSessionwithTransaction();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllInstances(final Class<T> type) {
		
		openCurrentSession();
		List<T> instances = (List<T>) getCurrentSession().createCriteria(type).list();
		closeCurrentSession();
		return instances;
	}
}