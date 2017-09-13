package com.gr.grquickrescue.services;

import java.util.List;

import javax.ejb.Remote;

import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.models.MapMarker;

@Remote
public interface AddressServiceRemote {

	public void saveAddress(Address entity);

	public void updateAddress(Address entity);

	public Address findAddressById(int addressId);

	public void deleteAddress(int addressId);

	public List<Address> findAllAddresses();

	public List<MapMarker> findMarkersByAccountId(int acccountId);
}