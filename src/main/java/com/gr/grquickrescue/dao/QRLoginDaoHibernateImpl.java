package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import com.gr.grquickrescue.models.QRLogin;
@Stateless
public class QRLoginDaoHibernateImpl implements QRLoginDao
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
	
	@Override
	public void saveLogin(QRLogin login) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().save(login);
		closeCurrentSessionwithTransaction();
	}

	@Override
	public void updateLogin(QRLogin login) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().update(login);
		closeCurrentSessionwithTransaction();
	}

	@Override
	public QRLogin findLoginById(int id) {
		// TODO Auto-generated method stub
		openCurrentSession();
		QRLogin login = (QRLogin) getCurrentSession().get(QRLogin.class, id);
		closeCurrentSession();
		return login;
	}

	@Override
	public QRLogin findLoginByUsername(String username) {
		openCurrentSession();
		Criteria cr = getCurrentSession().createCriteria(QRLogin.class).add(Restrictions.eq("username", username));
		QRLogin login = (QRLogin) cr.uniqueResult();
		return login;
	}

	@Override
	public void deleteLogin(QRLogin login) {
		// TODO Auto-generated method stub
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(login);
		closeCurrentSessionwithTransaction();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<QRLogin> findAllLogins() {
		// TODO Auto-generated method stub
		openCurrentSession();
		List<QRLogin> logins = (List<QRLogin>) getCurrentSession().createQuery("from QRLogin").list();
		closeCurrentSession();
		return logins;
	}

}
