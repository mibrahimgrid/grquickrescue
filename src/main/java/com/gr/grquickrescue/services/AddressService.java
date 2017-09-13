package com.gr.grquickrescue.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.google.code.geocoder.model.LatLng;
import com.gr.grquickrescue.dao.AccountDao;
import com.gr.grquickrescue.dao.AccountDaoHibernateImpl;
import com.gr.grquickrescue.dao.AddressDao;
import com.gr.grquickrescue.dao.AddressDaoHibernateImpl;
import com.gr.grquickrescue.dao.ContactDao;
import com.gr.grquickrescue.dao.ContactDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.Contact;
import com.gr.grquickrescue.models.MapMarker;
import com.gr.grquickrescue.utils.GeocodingUtility;

@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class AddressService implements AddressServiceRemote {

	private static AddressDao addressDao;
	private static ContactDao contactDao;
	public AddressService() {
		contactDao = (ContactDaoHibernateImpl)DaoManager.getInstance(ContactDao.class.getName()); 
		addressDao = (AddressDaoHibernateImpl) DaoManager.getInstance(AddressDao.class.getName());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveAddress(Address address) {
		try {
			LatLng latlng = GeocodingUtility.getLocation(address.getCity() + ", " + address.getCountry());
			address.setLatitude(latlng.getLat().doubleValue());
			address.setLongitude(latlng.getLng().doubleValue());
			addressDao.saveInstance(address);
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateAddress(Address entity) {
		try {
			addressDao.updateInstance(entity);
		} catch (Exception e) {
					e.printStackTrace();
			}
	}

	@Override
	public Address findAddressById(int id) {
		Address address = addressDao.findInstanceById(id, Address.class);
		return address;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAddress(int id) {
		try {
			Address address = addressDao.findInstanceById(id, Address.class);
			addressDao.deleteInstance(address);
		} catch (Exception e) {
					e.printStackTrace();
		}
		
	}

	@Override
	public List<Address> findAllAddresses() {
		List<Address> addresses = addressDao.findAllInstances(Address.class);
		return addresses;
	}
	@Override
	public List<MapMarker> findMarkersByAccountId(int accountId){
		List<MapMarker> markers = new ArrayList<MapMarker>();
		List<Contact> contacts = contactDao.findContactsByAccountId(accountId);
		for (Contact contact : contacts) {
			markers.add(new MapMarker(contact.getAddress().getLatitude(), contact.getAddress().getLongitude(), contact.getFirstName()+":"+contact.getAddress().getCountry()) );
		}
		return markers;
	}

}