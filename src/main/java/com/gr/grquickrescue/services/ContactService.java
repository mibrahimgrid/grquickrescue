package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.gr.grquickrescue.dao.AlertProfileDao;
import com.gr.grquickrescue.dao.ContactDao;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.dao.QRLoginDao;
import com.gr.grquickrescue.models.AlertProfile;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.QRLogin;
import com.gr.grquickrescue.utils.PasswordUtility;

@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class ContactService implements ContactServiceRemote {

	private static ContactDao contactDao;
	private static QRLoginDao loginDao;
	private static AlertProfileDao alertDao;
	public ContactService() {
		alertDao = (AlertProfileDao)DaoManager.getInstance(AlertProfileDao.class.getName());
		contactDao = (ContactDao) DaoManager.getInstance(ContactDao.class.getName());
		loginDao = (QRLoginDao) DaoManager.getInstance(QRLoginDao.class.getName());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveContact(Contact contact) {
		
		try {
			contactDao.saveInstance(contact);
			List<AlertProfile> alerts = alertDao.findAlertProfilesByAccountId(contact.getAccount().getId());
			for (AlertProfile alertProfile : alerts) {
				if(alertProfile.getCity().equals(contact.getAddress().getCity())) {
					//EmailUtility.sendAlertEmail(contact.getEmail(), alertProfile.getCity());
				}
				if(alertProfile.getCountry().equals(contact.getAddress().getCountry())) {
					//EmailUtility.sendAlertEmail(contact.getEmail(), alertProfile.getCountry());
				}
			}
			if(contact.isHasLogin()) 
			{
				QRLogin contactLogin = new QRLogin(contact.getEmailAddress(),PasswordUtility.generateRandomPassword());
				loginDao.saveInstance(contactLogin);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateContact(Contact entity) {
		try {
			contactDao.updateInstance(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Contact findContactById(int id) {
		Contact contact = contactDao.findInstanceById(id, Contact.class);
		return contact;

	}

	@Override
	public Contact findContactByEmail(String email) {
		return contactDao.findContactByEmail(email);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteContact(int id) {
		try {
			Contact contact = contactDao.findInstanceById(id, Contact.class);
			contactDao.deleteInstance(contact);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public List<Contact> findAllContacts() {
		List<Contact> contacts = contactDao.findAllInstances(Contact.class);
		return contacts;
	}

	public List<Contact> findContactsByAccountId(int accountId) {
		return contactDao.findContactsByAccountId(accountId);
	}
}