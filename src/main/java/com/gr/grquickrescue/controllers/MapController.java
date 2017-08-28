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
public class MapController extends Address{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private AddressServiceRemote addressService;
	
	private List<Address> addresses;
	private String test;
	@PostConstruct
	public void init() 
	{
		test = "this is a test";
		addressService = (AddressServiceRemote) ServiceManager.getInstance(AddressServiceRemote.class.getName());
		addresses = addressService.findAllAddresses();
	}
	public String testJs() 
	{
		return test;
	}
	public List<Address> getAddresses()
	{
		return addresses;
	}
	public void setAddresses(List<Address> addresses) 
	{
		this.addresses = addresses;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}

}
