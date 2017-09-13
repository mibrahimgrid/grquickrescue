package com.gr.grquickrescue.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.gr.grquickrescue.models.Contact;

public class ContactDaoHibernateImpl extends GenericDaoHibernateImpl<Contact, Integer> implements ContactDao
{
	
	@SuppressWarnings("unchecked")
	public List<Contact> findContactsByAccountId(int accountId)
	{
		openCurrentSession();
		List<Contact> contacts = (List<Contact>)getCurrentSession().createQuery("from Contact where accountID = "+accountId).list();
		closeCurrentSession();
		return contacts;
	}
	@Override
	public Contact findContactByEmail(String emailAddress) {
		openCurrentSession();
		Criteria cr = getCurrentSession().createCriteria(Contact.class).add(Restrictions.eq("emailAddress", emailAddress));
		Contact contact = (Contact) cr.uniqueResult();
		return contact;
	}
}