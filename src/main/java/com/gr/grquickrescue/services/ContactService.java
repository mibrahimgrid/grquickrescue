package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gr.grquickrescue.dao.ContactDao;
import com.gr.grquickrescue.dao.ContactDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Contact;

@Stateless
public class ContactService implements ContactServiceRemote
{
	@EJB
	private static ContactDao contactDao;

	public ContactService()
	{
		contactDao = (ContactDaoHibernateImpl) DaoManager.getInstance(ContactDao.class.getName());
	}
	@Override
	public void saveContact(Contact enetity)
	{
		contactDao.saveContact(enetity);

	}
	@Override
	public void updateContact(Contact entity)
	{
		contactDao.updateContact(entity);
	}
	@Override
	public Contact findContactById(int id)
	{
		Contact contact = contactDao.findContactById(id);
		return contact;

	}
	@Override
	public void deleteContact(int id)
	{
		Contact contact = contactDao.findContactById(id);
		contactDao.deleteContact(contact);
	}
	@Override
	public List<Contact> findAllContacts()
	{
		List<Contact> contacts = contactDao.findAllContacts();
		return contacts;
	}
	@Override
	public void deleteAllContacts()
	{
		contactDao.deleteAllContacts();
	}
}
