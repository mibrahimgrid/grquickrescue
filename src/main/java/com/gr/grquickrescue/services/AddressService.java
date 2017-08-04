package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gr.grquickrescue.dao.AddressDao;
import com.gr.grquickrescue.dao.AddressDaoHibernateImpl;
import com.gr.grquickrescue.dao.DaoManager;
import com.gr.grquickrescue.models.Address;

@Stateless
public class AddressService implements AddressServiceRemote 
{
	@EJB
	private static AddressDao addressDao;

	public AddressService() {
		addressDao = (AddressDaoHibernateImpl) DaoManager.getInstance(AddressDao.class.getName());
	}
	@Override
	public void saveAddress(Address entity) {
		addressDao.saveAddress(entity);
	}
	@Override
	public void updateAddress(Address entity) {
		addressDao.updateAddress(entity);
	}
	@Override
	public Address findAddressById(int id) {
		Address address = addressDao.findAddressById(id);
		return address;
	}
	@Override
	public void deleteAddress(int id) {
		Address address = addressDao.findAddressById(id);
		addressDao.deleteAddress(address);
	}
	@Override
	public List<Address> findAllAddresses() {
		List<Address> addresses = addressDao.findAllAddresses();
		return addresses;
	}
	@Override
	public void deleteAllAddresses() {
		addressDao.deleteAllAddresses();
	}

}