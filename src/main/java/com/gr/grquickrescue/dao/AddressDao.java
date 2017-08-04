package com.gr.grquickrescue.dao;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Address;
@Remote
public interface AddressDao
{
	public void saveAddress(Address Entity);

	public void updateAddress(Address Entity);

	public Address findAddressById(int id);

	public void deleteAddress(Address Entity);

	public List<Address> findAllAddresses();

	public void deleteAllAddresses();
}