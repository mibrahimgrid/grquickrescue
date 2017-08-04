package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Address;
@Remote
public interface AddressServiceRemote {

	public void saveAddress(Address entity);
	public void updateAddress(Address entity);
	public Address findAddressById(int addressId);
	public void deleteAddress(int addressId);
	public List<Address> findAllAddresses();
	public void deleteAllAddresses();

}
