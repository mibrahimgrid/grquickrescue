package com.gr.grquickrescue.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.gr.grquickrescue.models.Address;
import com.gr.grquickrescue.services.AddressServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;

@ManagedBean
@RequestScoped
public class AddressController
{
	@EJB
	private AddressServiceRemote addressService;
	
	private List<Address> addresses;
	
	@PostConstruct
	public void init() 
	{
		addressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		addresses = addressService.findAllAddresses();
	}
	public List<Address> getAddresses()
	{
		return addresses;
	}
	public void setAddresses(List<Address> addresses) 
	{
		this.addresses = addresses;
	}
}
