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
	public void saveContact(Contact entity)
	{
		contactDao.saveContact(entity);

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
	public Contact findContactByEmail(String email) 
	{
		return contactDao.findContactByEmail(email);
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
	public List<Contact> findContactsByAccountId(int accountId)
	{
		return contactDao.findContactsByAccountId(accountId);
	}
	@Override
	public void deleteAllContacts()
	{
		contactDao.deleteAllContacts();
	}
}
